package it.unisa.exceptions;

public class DBParameterNotInitializedException extends RuntimeException{
    public DBParameterNotInitializedException() {
        super();
    }

    public DBParameterNotInitializedException(String message) {
        super(message);
    }
}
