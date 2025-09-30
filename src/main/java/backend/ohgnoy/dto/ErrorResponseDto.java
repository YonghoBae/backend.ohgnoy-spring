// 패키지 선언: API 응답 형식을 정의하는 DTO 패키지
package backend.ohgnoy.dto;

// Lombok 어노테이션: 모든 필드에 대한 getter 메서드와 생성자를 자동 생성
import lombok.AllArgsConstructor;
import lombok.Getter;

// API 에러 응답 본문 형식을 정의하는 DTO 클래스
@Getter // code, message 필드에 대한 getter 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 초기화하는 생성자 자동 생성
public class ErrorResponseDto {

    // 에러 코드 값 (예: 4090)
    private final int code;

    // 에러 메시지 (예: "Email already exists.")
    private final String message;
}
