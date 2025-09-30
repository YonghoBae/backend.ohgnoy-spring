package backend.ohgnoy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 로그인 성공 시 반환할 사용자 정보 DTO
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Integer userId;   // 사용자 ID
    private String email;     // 이메일
    private String nickname;  // 닉네임
}
