package br.com.amaro.SIF.config.exceptions.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter @AllArgsConstructor
public class ErroDTO {
    private String message;
    private Date timestamp;
}
