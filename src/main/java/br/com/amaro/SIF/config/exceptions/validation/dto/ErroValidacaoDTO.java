package br.com.amaro.SIF.config.exceptions.validation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class ErroValidacaoDTO extends ErroDTO {

    private List<String> erros = new ArrayList<>();

    public ErroValidacaoDTO(String message, Date timestamp, List<String> erros) {
        super(message, timestamp);
        this.erros.addAll(erros);
    }
}
