package br.com.amaro.SIF.form;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String password;

    public UsernamePasswordAuthenticationToken toAuth() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
