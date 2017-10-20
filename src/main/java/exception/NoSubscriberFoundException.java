package exception;

public class NoSubscriberFoundException extends Throwable {
    public NoSubscriberFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
