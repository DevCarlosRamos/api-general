package uk.carlosramos.api.errores;

public class ClaveIncorrectaException extends RuntimeException {
    public ClaveIncorrectaException(String message) {
        super(message);
    }
}

