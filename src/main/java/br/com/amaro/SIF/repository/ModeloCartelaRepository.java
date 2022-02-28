package br.com.amaro.SIF.repository;


import br.com.amaro.SIF.repository.models.ModeloCartela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloCartelaRepository extends JpaRepository<ModeloCartela, Long> {

    ModeloCartela getByCodigoConvite(String codigo);

}
