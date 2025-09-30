package backend.ohgnoy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // 이 메소드가 반환하는 PasswordEncoder 객체를 Spring 컨테이너가 Bean으로 관리
    // 이렇게 등록하면, 다른 클래스에서 @Autowired나 생성자 주입을 통해 이 객체를 가져다 쓸 수 있다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        //BcryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 구현체 중 하나
        //같은 비밀번호를 암호화해도 매번 결과가 달라지는 Salting 기능이 내장
        return new BCryptPasswordEncoder();
    }

    // 메소드가 반환하는 SecurityFilterChain 객체를 Bean으로 등록하여, Spring Security의 전반적인 동작을 구성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //HttpSecurity 객체를 사용하여 HTTP 요청에 대한 보안 규칙을 연쇄적으로 설정
        http
                // .csrf(): Cross-Site Request Forgery(사이트 간 요청 위조) 공격을 방어하는 기능입니다.
                // .disable(): 하지만 JWT 기반의 stateless API에서는 서버에 세션 상태를 저장하지 않으므로,
                // CSRF 토큰을 검증할 필요가 없습니다. 따라서 이 기능을 비활성화합니다.
                .csrf(csrf -> csrf.disable())

                // .sessionManagement(): 세션 관리 정책을 설정합니다.
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS):
                // 세션을 생성하지도, 기존 세션을 사용하지도 않도록 설정합니다. 모든 요청을 독립적인 트랜잭션으로 취급하게 되어,
                // 서버가 상태를 유지할 필요 없는 진정한 RESTful API를 만들 수 있습니다. JWT 인증의 핵심 설정입니다.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // .authorizeHttpRequests(): HTTP 요청에 대한 접근 권한을 설정합니다.
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers(...): 특정 URL 패턴을 지정합니다.
                        .requestMatchers(
                                "/user/register",       // 회원가입 API
                                "/user/login",          // 로그인 API
                                "/swagger-ui/**",       // Swagger UI 페이지 및 관련 리소스
                                "/v3/api-docs/**"       // Swagger API 명세서(JSON)
                        )
                        // .permitAll(): 위에서 지정한 URL 경로에 대해서는 인증(로그인) 없이 모든 사용자의 접근을 허용합니다.
                        .permitAll()
                        // .anyRequest(): 위에서 설정한 경로 외의 모든 나머지 요청을 의미합니다.
                        // .authenticated(): 나머지 모든 요청은 반드시 인증된 사용자만 접근할 수 있도록 요구합니다.
                        // 즉, 유효한 JWT를 헤더에 포함한 요청만 통과됩니다.
                        .anyRequest().authenticated()
                );

        //설정이 완료된 HttpSecurity 객체를 빌드하여 Security
        return http.build();
    }
}
