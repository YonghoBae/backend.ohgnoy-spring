package backend.ohgnoy.exception;

public class InvalidPasswordException extends CustomException {
    public InvalidPasswordException() {
        super(ErrorCode.PASSWORD_INVALID);
    }
}
