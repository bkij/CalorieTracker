package agh.edu.exceptions;

public class PersistenceException extends RuntimeException {
    public PersistenceException() {
        super();
    }
    public PersistenceException(String message) {
        super(message);
    }
}
