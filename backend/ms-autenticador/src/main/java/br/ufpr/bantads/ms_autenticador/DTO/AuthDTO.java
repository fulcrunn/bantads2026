package br.ufpr.bantads.ms_autenticador.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera Getters, Setters
@AllArgsConstructor // Gera construtor com todos os argumentos
@NoArgsConstructor


public class AuthDTO {
    private String login;
    private String senha; // Existe por apenas poucos segundos, depois é apagada por segurança
    private TipoCliente tipo;  
    public enum TipoCliente {
        CLIENTE,
        GERENTE,
        ADMINISTRADOR
    }
}
