package br.com.amaro.SIF.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerCartelasDTO {
    private String username;
    private List<CartelaDTO> cartelas = new ArrayList<>();

    public OwnerCartelasDTO(String username, List<CartelaDTO> cartelas) {
        this.username = username;
        this.cartelas.addAll(cartelas);
    }
}
