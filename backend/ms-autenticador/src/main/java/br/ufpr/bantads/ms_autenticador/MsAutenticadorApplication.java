package br.ufpr.bantads.ms_autenticador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MsAutenticadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAutenticadorApplication.class, args);
	}

	@Bean // Exposes BCryptPasswordEncoder as a Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
