package br.com.amaro.SIF.config.exceptions;

public class InvalidInfoException extends RuntimeException{

    public InvalidInfoException(String errorMessage) {
        super(errorMessage);
    }
    
}
