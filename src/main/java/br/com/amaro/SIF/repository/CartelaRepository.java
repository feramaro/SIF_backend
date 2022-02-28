package br.com.amaro.SIF.repository;

import br.com.amaro.SIF.repository.models.Cartela;
import br.com.amaro.SIF.repository.models.ModeloCartela;
import br.com.amaro.SIF.repository.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CartelaRepository extends JpaRepository<Cartela, UUID> {

    Cartela findCartelaBySerie(String serie);

    List<Cartela> findCartelasByOwner(Usuario owner);
    boolean existsCartelaByOwnerAndModeloCartelaAndCompletaFalse(Usuario owner, ModeloCartela modeloCartela);
}
