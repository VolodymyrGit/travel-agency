package vml.travelagency.exceptions;

public class NullMethodParameterException extends RuntimeException{

    public NullMethodParameterException() {}

    public NullMethodParameterException(String message) {
        super(message);
    }
}
