package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.repository.models.Cartela;
import lombok.Data;

@Data
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
