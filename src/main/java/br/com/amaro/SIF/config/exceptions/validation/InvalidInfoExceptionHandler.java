package br.com.amaro.SIF.config.exceptions.validation;


import br.com.amaro.SIF.config.exceptions.InvalidInfoException;
import br.com.amaro.SIF.config.exceptions.validation.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class InvalidInfoExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidInfoException.class)
    public ErroDTO handle(InvalidInfoException exception) {
        return new ErroDTO(exception.getMessage(), new Date());
    }
}
