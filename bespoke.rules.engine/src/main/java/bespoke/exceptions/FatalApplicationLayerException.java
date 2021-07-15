package bespoke.exceptions;

public class FatalApplicationLayerException extends RuntimeException {
    public FatalApplicationLayerException(String message) {
        super(message);
    }

    public FatalApplicationLayerException(String m, Throwable t) {
        super(m,t);
    }
}
