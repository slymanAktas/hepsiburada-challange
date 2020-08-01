package utils;

public class TestException extends RuntimeException {

    private TestException(String message) {
        super(message);
    }

    public TestException(Object detailMessage) {
        this(String.valueOf(detailMessage));
        if (detailMessage instanceof Throwable) {
            initCause((Throwable) detailMessage);
        }
    }
}
