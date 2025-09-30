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

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 주입 (Lombok)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 Bean으로 등록한 객체가 주입됨
    private final JwtUtil jwtUtil; // JwtUtil 주입

    // 회원가입
    public UserResponseDto registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        // --- 비밀번호 암호화 로직 추가 ---
        // 사용자가 입력한 비밀번호(평문)를 암호화하여 다시 설정
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new UserResponseDto(user.getUserId(), user.getEmail(), user.getNickname());
    }

    // 로그인
    public String loginUser(UserLoginRequestDto userLoginRequestDto) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        // --- 암호화된 비밀번호 비교 로직 ---
        // matches(평문 비밀번호, 암호화된 비밀번호) -> 일치하면 true, 아니면 false 반환
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        // --- 로그인 성공 시 JWT 생성 및 반환 ---
        return jwtUtil.generateToken(user);
    }
}