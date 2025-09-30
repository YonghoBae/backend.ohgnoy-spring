package backend.ohgnoy.config;

import backend.ohgnoy.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.Date;

//@Component: Spring이 이 클래스를 Bean으로 관리하도록 하여, 다른 곳에서 주입해서 쓸 수 있게 함
@Component
public class JwtUtil {
    //JWT의 유효 기간 (밀리초 단위). 1시간으로 설정
    private final Long EXPIRATION_TIME = 1000L * 60 * 60;

    //JWT 서명에 사용할 비밀 키
    //application.properties 파일에서 값을 가져옴
    @Value("${jwt.secret}")
    private String secretKey;

    //비밀 키를 기반으로 암호화된 Key 객체를 생성
    private Key getSigninKey(){
        //비밀 키 문자열을 UTF-8 인코딩의 바이트 배열로 변환
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        //변환된 바이트 배열을 사용하여 HMAC-SHA 알고리즘에 맞는 Key 객체를 생성하여 반환
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /*
    * 사용자 이메일을 기반으로 JWT를 생성하는 메소드
    * 이메일(sub), userId, nickname 정보를 포함
     */
    public String generateToken(User user){
        //JWT의 Payload 부분에 담을 추가 정보(Custom Claims)를 Map 형태로 구성
        //이 정보들은 나중에 토큰을 해석하여 바로 꺼내 쓸 수 있어 유리
        Map<String, Object> claims = Map.of(
                "userId", user.getUserId(),
                "nickname", user.getNickname()
        );

        Date now = new Date(); //현재 시간 나타내는 Data 객체 생성
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);//현재 시간에 유효 시간을 더해서 만료 시간 계산

        // Jwts.builder()를 사용하여 JWT 생성 및 반환
        return Jwts.builder()
                // .setSubject() : 토큰의 '주체'를 설정, 주로 사용자를 식별할 수 있는 고유한 값을 사용
                .setSubject(user.getEmail())
                // .addClaims() : 위에서 정의한 추가 정보를 Payload에 담는다
                .addClaims(claims)
                // .setIssuedAt() : 토큰이 발급된 시간을 설정
                .setIssuedAt(now)
                // .setExpiration() : 토큰의 만료 시간을 설정, 이 시간이 지나면 토큰은 유효하지 않게 됨
                .setExpiration(expiration)
                // .signWith() : 토큰에 서명을 추가. getSigningKey()로 가져온 비밀 키와 HS256 알고리즘을 사용
                // 이 서명을 통해 토큰이 위조되지 않았음을 증명할 수 있다.
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                // .compact() : 모든 설정을 바탕으로 최종적인 JWT 문자열을 생성
                .compact();
    }

    /*
    * JWT 문자열을 해석(파싱)하여 그 안에 담긴 모든 Claims 정보를 추출
    * @param token 해석할 JWT 문자열
    * @return 토큰의 Payload에 담긴 Claims 객체
     */
    private Claims getClaimsFromToken(String token) {
        //Jwts.parserBuilder()를 사용하여 JWT 파서를 생성
        return Jwts.parserBuilder()
                // .setSigningKey() : 토큰을 검증할 때 사용할 비밀 키를 설정. 발급할 때 사용한 키와 동일
                .setSigningKey(getSigninKey())
                // .build() : 파서를 빌드
                .build()
                // .parseClaimsJws(token): 토큰 문자열을 파싱하여 서명을 검증하고, Claims를 추출
                // 만약 서명이 다르거나 토큰이 변조되었다면 여기서 예외가 발생
                .parseClaimsJwt(token)
                // .getBody(): 파싱된 JWT의 Payload(Body) 부분, 즉 Claims 객체를 반환
                .getBody();
    }

    /*
    * JWT에서 사용자 이메일(Subject)를 추출
     */
    public String getEmailFromToken(String token) {
        //getALlClaimsFromToken() 메소드를 호출하여 전체 Claims를 얻어온 뒤,
        //.getSubject() 메소드로 '주체'(이메일)을 반환
        return getClaimsFromToken(token).getSubject();
    }

    /*
    * JWT가 유효한지 (변조되지 않았는지, 만료되지 않았는지) 검증
     */
    public boolean validateToken(String token) {
        try{
            //토큰 파싱을 시도, 파싱 과정에서  서명 검증, 만료 시간 확인
            Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token);
            //예외가 발생하지 않고 성공적으로 파싱되었다면, 토큰은 유효한 것이므로 true 반환
            return true;
        }catch(Exception e){
            //파싱 과정에서 어떤 종류의 예외라도 발생하면
            //토큰이 유효하지 않은 것이므로 false를 반환
            return false;
        }
    }
}
