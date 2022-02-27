package br.com.amaro.SIF.config.excpetions;

public class CartelaException extends RuntimeException{
    public CartelaException(String errorMessage) {
        super(errorMessage);
    }
}
