package backend.ohgnoy.exception;

import lombok.Getter;

// 모든 커스텀 예외의 부모 클래스
//RuntimeException을 상속받아 런타임 예외 처리 가능
@Getter
public class CustomException extends RuntimeException {
    // 에러 코드 정보를 담는 필드
    private final ErrorCode errorCode;

    // 에러 코드만 받는 생성자
    // super(errorCode.getMessage())를 호출하여 RuntimeException에 메시지 전달
    public CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

// RuntimeException 참고용
//public class RuntimeException extends Exception {
//    private String message;
//
//    public RuntimeException(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//}
