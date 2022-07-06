package livraria.imperial.user;

import livraria.imperial.user.dtos.CreateUserRequest;
import livraria.imperial.user.dtos.PartialUpdateUserRequest;
import livraria.imperial.user.dtos.UserEntity;
import livraria.imperial.user.dtos.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UserMapper {


        UserEntity mapCreateRequestToEntity(CreateUserRequest createUserRequest);

        UserResponse mapEntityToResponse(UserEntity user);

        List<UserResponse> mapListToResponse(List<UserEntity> users);

}
