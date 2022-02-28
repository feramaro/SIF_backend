package br.com.amaro.SIF.config.exceptions.validation;

import br.com.amaro.SIF.config.exceptions.CartelaException;
import br.com.amaro.SIF.config.exceptions.validation.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CartelaExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartelaException.class)
    public ErroDTO handle(CartelaException excpetion) {
        return new ErroDTO(excpetion.getMessage(), new Date());
    }
}
