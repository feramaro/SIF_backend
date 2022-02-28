package br.com.amaro.SIF.repository;

import br.com.amaro.SIF.repository.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByUserName(String username);
}
