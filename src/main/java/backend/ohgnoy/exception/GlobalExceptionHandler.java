// 패키지 선언: 예외 처리 전역 설정이 위치하는 패키지
package backend.ohgnoy.exception;

// 필요한 클래스 임포트
import backend.ohgnoy.dto.ErrorResponseDto; // 에러 응답 DTO 임포트
import org.springframework.http.ResponseEntity; // HTTP 응답을 생성하는 클래스
import org.springframework.web.bind.annotation.ExceptionHandler; // 예외 처리 메서드 지정 어노테이션
import org.springframework.web.bind.annotation.RestControllerAdvice; // REST API 전역 예외 처리 어노테이션

// 모든 REST 컨트롤러에서 발생하는 예외를 처리하는 전역 핸들러 클래스 선언
@RestControllerAdvice
public class GlobalExceptionHandler {

    // CustomException이 발생했을 때 호출되는 메서드
    @ExceptionHandler(CustomException.class) // CustomException 처리 메서드 지정
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException e) {

        // 발생한 예외에서 에러 코드 정보 추출
        ErrorCode errorCode = e.getErrorCode();

        // 에러 응답 DTO 생성 (코드와 메시지 전달)
        ErrorResponseDto errorResponse = new ErrorResponseDto(errorCode.getCode(), errorCode.getMessage());

        // HTTP 상태 코드 409(CONFLICT)와 함께 에러 응답 반환
        return ResponseEntity.status(409).body(errorResponse);
    }
}
