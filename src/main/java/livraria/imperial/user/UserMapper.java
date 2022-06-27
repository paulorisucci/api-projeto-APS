package livraria.imperial.user;

import livraria.imperial.user.dtos.CreateUserRequest;
import livraria.imperial.user.dtos.UserEntity;
import livraria.imperial.user.dtos.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {


        UserEntity mapRequestToEntity(CreateUserRequest createUserRequest);

        UserResponse mapEntityToResponse(UserEntity user);

        List<UserResponse> mapListToResponse(List<UserEntity> users);

}
