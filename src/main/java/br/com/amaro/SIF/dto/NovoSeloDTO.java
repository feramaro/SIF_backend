package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.form.NovoSeloForm;
import br.com.amaro.SIF.models.Cartela;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class NovoSeloDTO {
    private String serie;
    private String username;
    private Integer qtdSelos;
    private boolean completa;

    public NovoSeloDTO (Cartela cartela) {
        this.serie = cartela.getSerie();
        this.username = cartela.getOwner().getUsername();
        this.qtdSelos = cartela.getSelos();
        this.completa = cartela.isCompleta();
    }
}
