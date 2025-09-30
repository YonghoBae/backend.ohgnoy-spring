package backend.ohgnoy.exception;

//이메일 중복 상황에서 발생시키는 구체적인 예외 클래스
//CustomException을 상속받아 에러 코드 정보를 포함
public class EmailAlreadyExistsException extends CustomException {

    // 기본 생성자
    // 상위 클래스인 CustomException 생성자에 EMAIL_DUPLICATE 에러 코드를 전달
    public EmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_DUPLICATE);//에러 코드로 EMAIL_DUPLICATE 사용
    }
}

