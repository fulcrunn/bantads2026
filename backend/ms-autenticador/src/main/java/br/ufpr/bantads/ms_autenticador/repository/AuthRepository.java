package br.ufpr.bantads.ms_autenticador.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.ufpr.bantads.ms_autenticador.UsuarioAuth;
import java.util.Optional;

public interface AuthRepository extends MongoRepository<UsuarioAuth, String>{
    Optional<UsuarioAuth> findByLogin(String login);
}
