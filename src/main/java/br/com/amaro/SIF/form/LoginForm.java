package br.com.amaro.SIF.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter @AllArgsConstructor
public class LoginForm {
    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken toAuth() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
