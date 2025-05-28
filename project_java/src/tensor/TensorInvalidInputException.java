package tensor;

public class TensorInvalidInputException extends RuntimeException {
    public TensorInvalidInputException() {
        super();
    }

    public TensorInvalidInputException(String message) {
        super(message);
    }
}
