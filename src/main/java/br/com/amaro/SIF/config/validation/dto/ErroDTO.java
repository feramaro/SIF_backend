package br.com.amaro.SIF.config.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter @AllArgsConstructor
public class ErroDTO {
    private String message;
    private Date timestamp;
}
