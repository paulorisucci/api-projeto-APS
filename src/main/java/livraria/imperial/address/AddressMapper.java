package livraria.imperial.address;

import livraria.imperial.address.dto.AddressEntity;
import livraria.imperial.address.dto.AddressRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity mapRequestToEntity(AddressRequest addressRequest);
}
