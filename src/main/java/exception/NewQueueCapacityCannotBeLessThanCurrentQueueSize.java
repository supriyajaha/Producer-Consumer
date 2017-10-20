package exception;

public class NewQueueCapacityCannotBeLessThanCurrentQueueSize extends Throwable {
    public NewQueueCapacityCannotBeLessThanCurrentQueueSize(String exceptionMessage) {
        super(exceptionMessage);
    }
}
