package br.com.amaro.SIF.form;


import br.com.amaro.SIF.models.Usuario;
import br.com.amaro.SIF.models.enums.TipoUsuario;
import lombok.Getter;

@Getter
public class UsuarioRegisterForm {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String username;
    private String tipo;

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setTelefone(this.telefone);
        usuario.setUserName(this.username);
        usuario.setTipo(TipoUsuario.valueOf(this.tipo));
        return usuario;
    }
}
