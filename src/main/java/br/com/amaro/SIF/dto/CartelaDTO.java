package br.com.amaro.SIF.dto;


import br.com.amaro.SIF.repository.models.Cartela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class CartelaDTO {
    private String serie;
    private Integer selos;
    private boolean completa;

    public CartelaDTO(Cartela cartela) {
        this.serie = cartela.getSerie();
        this.selos = cartela.getSelos();
        this.completa = cartela.isCompleta();
    }
}
