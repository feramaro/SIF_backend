package br.com.amaro.SIF.dto;

import br.com.amaro.SIF.repository.models.ModeloCartela;
import lombok.Data;

import java.util.Date;

@Data
public class ModeloCartelaDTO {

    private final String descricao;
    private final Integer quantidadeSelos;
    private final String codigoConvite;
    private final Date dataExpiracao;
    private final boolean ativa;

    public ModeloCartelaDTO (ModeloCartela modeloCartela) {
        this.descricao = modeloCartela.getDescricao();
        this.quantidadeSelos = modeloCartela.getQuantidadeSelos();
        this.codigoConvite = modeloCartela.getCodigoConvite();
        this.dataExpiracao = modeloCartela.getDataExpiracao();
        this.ativa = modeloCartela.isAtiva();
    }
}
