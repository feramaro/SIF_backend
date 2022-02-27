package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.models.ModeloCartela;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ModeloCartelaDTO {

    private final String descricao;
    private final Integer quantidadeSelos;

    public ModeloCartelaDTO (ModeloCartela modeloCartela) {
        this.descricao = modeloCartela.getDescricao();
        this.quantidadeSelos = modeloCartela.getQuantidadeSelos();
    }
}
