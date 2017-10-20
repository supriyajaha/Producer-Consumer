package exception;

public class ConsumerAlreadySubscribedException extends Throwable {
    public ConsumerAlreadySubscribedException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
