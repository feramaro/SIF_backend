package br.com.amaro.SIF.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class NovoSeloForm {
    @NotNull @NotEmpty
    private String serie;
    @NotNull @Min(1) @Max(50)
    private Integer qtdSelos;
}
