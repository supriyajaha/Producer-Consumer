package exception;

public class InvalidDependentConsumers extends Throwable {
    public InvalidDependentConsumers(String exceptionMessage) {
        super(exceptionMessage);
    }
}
