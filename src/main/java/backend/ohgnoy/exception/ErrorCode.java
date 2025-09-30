/// 패키지 경로: 예외 코드 관리를 위한 패키지
package backend.ohgnoy.exception;

// Lombok 어노테이션: 모든 필드에 대한 Getter 메서드 자동 생성
import lombok.AllArgsConstructor;
import lombok.Getter;

//Enum 클래스: 에러 코드와 메시지 관리
@Getter //각 필드의 getter 메서드를 자동 생성 (ex: getCoe(), getMeassage())
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
public enum ErrorCode {
    EMAIL_DUPLICATE(4090, "Email already exists."),
    NICKNAME_DUPLICATE(4091,"Nickname already exists."),
    USER_NOT_FOUND(4040, "User not found."),
    PASSWORD_INVALID(4041,"Password Invalid"),
    POST_NOT_FOUND(4041,"Post not found");

    private final int code;
    private final String message;
}
