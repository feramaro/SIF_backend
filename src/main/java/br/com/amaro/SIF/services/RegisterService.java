package br.com.amaro.SIF.services;


import br.com.amaro.SIF.form.UsuarioRegisterForm;
import br.com.amaro.SIF.models.Usuario;
import br.com.amaro.SIF.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario novoUsuario(UsuarioRegisterForm form) {
        Usuario usuario = form.toUsuario();
        usuario.setSenha(encoder.encode(form.getSenha()));
        usuarioRepository.save(usuario);
        return null;
    }
}
