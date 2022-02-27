package br.com.amaro.SIF.repository;

import br.com.amaro.SIF.models.Cartela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartelaRepository extends JpaRepository<Cartela, UUID> {

    public Cartela findCartelaBySerie(String serie);
}
