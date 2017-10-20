package exception;

public class MessageNotValidException extends Throwable {
    public MessageNotValidException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
