package br.com.amaro.SIF.controllers;


import br.com.amaro.SIF.dto.TokenDTO;
import br.com.amaro.SIF.form.LoginForm;
import br.com.amaro.SIF.form.UsuarioRegisterForm;
import br.com.amaro.SIF.services.RegisterService;
import br.com.amaro.SIF.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sif_user")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RegisterService registerService;

    @PostMapping("/login")
    private ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken data = form.toAuth();
        try {
            Authentication auth = authenticationManager.authenticate(data);
            String token = tokenService.gerarToken(auth);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity registrar(@RequestBody @Valid UsuarioRegisterForm form) {
        try {
            registerService.novoUsuario(form);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
