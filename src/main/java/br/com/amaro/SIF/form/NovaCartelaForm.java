package br.com.amaro.SIF.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor
public class NovaCartelaForm {
    @NotNull
    private Long idModelo;
    @NotNull @NotEmpty
    private String username;
}
