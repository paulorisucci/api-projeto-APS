package livraria.imperial.user.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    Optional<AdminEntity> findByLoginAndPassword(String login, String password);

}
