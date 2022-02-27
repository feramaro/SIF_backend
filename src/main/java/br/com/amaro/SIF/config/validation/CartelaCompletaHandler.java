package br.com.amaro.SIF.config.validation;

import br.com.amaro.SIF.config.excpetions.CartelaException;
import br.com.amaro.SIF.config.validation.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CartelaCompletaHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartelaException.class)
    public ErroDTO handle(CartelaException excpetion) {
        return new ErroDTO(excpetion.getMessage(), new Date());
    }
}
