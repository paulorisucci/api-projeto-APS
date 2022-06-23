package livraria.imperial.user;

import livraria.imperial.user.dtos.UserEntity;
import livraria.imperial.user.dtos.UserResponse;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByLoginAndPassword(String login, String password);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByLogin(String login);

    @Mapper(componentModel = "spring")
    interface UserMapper {

        UserResponse mapEntityToResponse(UserEntity user);

        List<UserResponse> mapListToResponse(List<UserEntity> users);
    }
}
