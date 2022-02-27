package br.com.amaro.SIF.services;

import br.com.amaro.SIF.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.salt}")
    private String salt;
    @Value("${jwt.time}")
    private String expiracao;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date inicio = new Date();
        Date fim = new Date(inicio.getTime() + Long.parseLong(expiracao));
        return Jwts.builder()
                .setIssuer("API do Forum Alura")
                .setSubject(logado.getId().toString())
                .setIssuedAt(inicio)
                .setExpiration(fim)
                .signWith(SignatureAlgorithm.HS256,salt)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(salt).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Long convertTokenAndGetId(String authorization) {
        return getIdUsuario(authorization.substring(7));
    }

    public Long getIdUsuario(String token) {
        Claims claims =  Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
