package livraria.imperial.domain.model.repositories;

import livraria.imperial.domain.model.entities.AdminEntity;
import livraria.imperial.domain.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    Optional<AdminEntity> findByLoginAndPassword(String login, String password);

}
