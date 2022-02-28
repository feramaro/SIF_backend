package br.com.amaro.SIF.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ConviteForm {
    @NotNull @NotEmpty
    private String convite;
}
