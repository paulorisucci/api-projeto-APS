package livraria.imperial.domain.model.repositories;

import livraria.imperial.domain.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByLoginAndPassword(String login, String password);

}
