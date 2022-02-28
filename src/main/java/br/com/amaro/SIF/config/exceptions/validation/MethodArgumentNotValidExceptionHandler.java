package br.com.amaro.SIF.config.exceptions.validation;

import br.com.amaro.SIF.config.exceptions.validation.dto.ErroValidacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroValidacaoDTO handle(MethodArgumentNotValidException exception) {
        List<String> erros = new ArrayList<>();
        List<FieldError> exceptionErrors = exception.getBindingResult().getFieldErrors();
        exceptionErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            erros.add(message);
        });
        return new ErroValidacaoDTO("Ocorreu um erro de validação", new Date(), erros);
    }
}
