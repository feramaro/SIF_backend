package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.models.ModeloCartela;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
public class ModeloCartelaDTO {

    private final String descricao;
    private final Integer quantidadeSelos;
    private final Date dataExpiracao;
    private final boolean ativa;

    public ModeloCartelaDTO (ModeloCartela modeloCartela) {
        this.descricao = modeloCartela.getDescricao();
        this.quantidadeSelos = modeloCartela.getQuantidadeSelos();
        this.dataExpiracao = modeloCartela.getDataExpiracao();
        this.ativa = modeloCartela.isAtiva();
    }
}
