package tensor;

public class TensorInvalidDimensionException extends RuntimeException {
    public TensorInvalidDimensionException(){
        super();
    };

    public TensorInvalidDimensionException(String message) {
        super(message);
    }
}
