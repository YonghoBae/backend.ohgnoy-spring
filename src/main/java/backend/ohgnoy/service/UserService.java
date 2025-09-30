package backend.ohgnoy.service;

import backend.ohgnoy.config.JwtUtil;
import backend.ohgnoy.dto.UserLoginRequestDto;
import backend.ohgnoy.dto.UserResponseDto;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.exception.EmailAlreadyExistsException;
import backend.ohgnoy.exception.InvalidPasswordException;
import backend.ohgnoy.exception.UserNotFoundException;
import backend.ohgnoy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입 로직
    public UserResponseDto registerUser(User user) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        // --- [핵심] 비밀번호 암호화 ---
        // 사용자가 입력한 평문 비밀번호를 그대로 DB에 저장하면 매우 위험합니다.
        // passwordEncoder.encode() 메소드를 사용하여 비밀번호를 안전한 해시값으로 변환합니다.
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        // 변환된 암호화된 비밀번호를 User 객체에 다시 설정합니다.
        user.setPassword(encodedPassword);

        // 암호화된 비밀번호가 설정된 User 객체를 DB에 저장합니다.
        userRepository.save(user);

        // 클라이언트에게는 암호화되지 않은 사용자 정보를 담은 DTO를 반환합니다.
        return new UserResponseDto(user.getUserId(), user.getEmail(), user.getNickname());
    }

    public String loginUser(UserLoginRequestDto userLoginRequestDto) {
        // 1. 사용자 존재 여부 확인
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        // 2. [핵심] 비밀번호 일치 여부 확인
        // DB에 저장된 비밀번호는 암호화되어 있으므로, `==` 나 `.equals()` 로 비교할 수 없습니다.
        // passwordEncoder.matches() 메소드를 사용해야 합니다.
        // 이 메소드는 사용자가 입력한 평문 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교하여 일치 여부를 boolean으로 반환합니다.
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            // 비밀번호가 일치하지 않으면 예외를 발생시킵니다.
            throw new InvalidPasswordException();
        }

        // 3. [핵심] 로그인 성공 시, JWT 생성 및 반환
        // 비밀번호가 일치하면, 해당 사용자 정보를 담은 JWT를 생성하여 클라이언트에게 반환합니다.
        // 이제 클라이언트는 이 JWT를 저장해두고, 다음 요청부터는 이 토큰을 함께 보내야 합니다.
        return jwtUtil.generateToken(user);
    }
}
