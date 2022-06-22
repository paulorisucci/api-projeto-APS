package livraria.imperial.domain.model.repositories;

import livraria.imperial.domain.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByLoginAndPassword(String login, String password);

}
