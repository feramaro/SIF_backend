package br.com.amaro.SIF.form;

import br.com.amaro.SIF.models.ModeloCartela;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.Banner;

@Getter @AllArgsConstructor
public class ModeloCartelaForm {
    private String descricao;
    private Integer quantidadeSelos;
    private String brinde;
    private Integer diasExpiracao;

    public ModeloCartela toModeloCartela() {
        ModeloCartela modeloCartela = new ModeloCartela();
        modeloCartela.setDescricao(this.descricao);
        modeloCartela.setQuantidadeSelos(this.quantidadeSelos);
        modeloCartela.setBrinde(this.brinde);
        return modeloCartela;
    }
}
