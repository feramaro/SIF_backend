package br.com.amaro.SIF.form;

import br.com.amaro.SIF.repository.models.ModeloCartela;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ModeloCartelaForm {
    @NotNull @NotEmpty @Size(min = 5, max = 50)
    private String descricao;
    @NotNull @Min(2) @Max(30)
    private Integer quantidadeSelos;
    @NotNull @NotEmpty @Size(min = 5, max = 60)
    private String brinde;
    @NotNull
    private Integer diasExpiracao;

    public ModeloCartela toModeloCartela() {
        ModeloCartela modeloCartela = new ModeloCartela();
        modeloCartela.setDescricao(this.descricao);
        modeloCartela.setQuantidadeSelos(this.quantidadeSelos);
        modeloCartela.setBrinde(this.brinde);
        return modeloCartela;
    }
}
