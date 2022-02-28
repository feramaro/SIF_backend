package br.com.amaro.SIF.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ModeloCartelaUpdateForm {
    @NotNull
    private boolean ativa;

}
