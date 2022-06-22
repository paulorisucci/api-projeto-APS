package livraria.imperial.domain.model.mappers;


import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.responses.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapEntityToResponse(UserEntity user);

    List<UserResponse> mapListToResponse(List<UserEntity> users);
}
