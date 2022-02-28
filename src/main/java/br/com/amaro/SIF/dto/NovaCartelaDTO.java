package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.repository.models.Cartela;
import lombok.Data;


@Data
public class NovaCartelaDTO {
    private String serie;
    private String username;

    public NovaCartelaDTO(Cartela cartela) {
        this.serie = cartela.getSerie();
        this.username = cartela.getOwner().getUsername();
    }
}
