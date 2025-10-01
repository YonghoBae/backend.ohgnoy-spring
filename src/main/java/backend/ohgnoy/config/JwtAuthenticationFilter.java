package backend.ohgnoy.config;

import backend.ohgnoy.entity.User;
import backend.ohgnoy.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. HTTP 헤더에서 "Authorization" 값을 가져온다
        String authorizationHeader = request.getHeader("Authorization");

        // 2. 토큰이 존재하고 "Bearer "로 시작하는지 확인한다
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); //"Bearer "이후 순수 토큰값 추출
            // 3. 토큰이 유효한지 검증한다.
            if(jwtUtil.validateToken(token)) {
                // 4. 토큰에서 이메일을 추출하고 DB에서 사용자를 찾는다
                String email = jwtUtil.getEmailFromToken(token);
                User user = userRepository.findByEmail(email).orElse(null);

                if(user != null) {
                    // 5. Spring Security의 Context에 인증된 사용자 정보를 저장한다
                        //추후 컨트롤러에서 @AuthenticationPrincipal 등을 통해 사용자 정보를 바로 꺼내 쓸 수 있다.
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        //6. 다음 필터로 요청을 넘깁니다.
        filterChain.doFilter(request,response);
    }
}
