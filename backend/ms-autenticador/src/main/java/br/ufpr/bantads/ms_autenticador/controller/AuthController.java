package br.ufpr.bantads.ms_autenticador.controller;

import java.util.Date;
import java.util.Optional;
import java.security.Key;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.bantads.ms_autenticador.UsuarioAuth;
import br.ufpr.bantads.ms_autenticador.DTO.AuthDTO;
import br.ufpr.bantads.ms_autenticador.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/auth") // Todas as rotas deste controller começam com /auth
public class AuthController {

    // O Spring injeta magicamente o valor que está no application.properties!
    @Value("${jwt.secret}")
    private String jwtSecret;

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO dadosLogin) {
        Optional<UsuarioAuth> usuarioValidado = authService.fazerLogin(dadosLogin.getLogin(), dadosLogin.getSenha());

        if (usuarioValidado.isPresent()) {
            long tempoExpiracaoTeste = 60000; // 1 minuto

            // Converte a String do segredo para uma Chave Criptográfica
            Key chaveAssinatura = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            String meuTokenJwt = Jwts.builder()
                    .setSubject(usuarioValidado.get().getLogin())
                    .claim("perfil", usuarioValidado.get().getPerfil())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracaoTeste))
                    .signWith(chaveAssinatura) // Agora usamos a chave convertida!
                    .compact();

            return ResponseEntity.ok(meuTokenJwt);
        } else {
            // Retorna 401 Unauthorized sem token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
