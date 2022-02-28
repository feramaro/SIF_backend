package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.models.Cartela;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovaCartelaDTO {
    private String serie;
    private String username;

    public NovaCartelaDTO(Cartela cartela) {
        this.serie = cartela.getSerie();
        this.username = cartela.getOwner().getUsername();
    }
}
