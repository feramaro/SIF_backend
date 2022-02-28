package br.com.amaro.SIF.form;


import br.com.amaro.SIF.repository.models.Usuario;
import br.com.amaro.SIF.repository.models.enums.TipoUsuario;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioRegisterForm {
    @NotNull @NotEmpty @Size(min = 8, max = 60)
    private String nome;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String senha;
    @NotNull @NotEmpty @Size(max = 11)
    private String telefone;
    @NotNull @NotEmpty @Size(min = 4, max = 16)
    private String username;
    @NotNull @NotEmpty
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
