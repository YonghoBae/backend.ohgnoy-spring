package backend.ohgnoy.exception;

public class NicknameAlreadyExistsException extends CustomException {
    public NicknameAlreadyExistsException(){
        super(ErrorCode.NICKNAME_DUPLICATE);
    }
}