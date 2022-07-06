package livraria.imperial.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity mapRequestToEntity(ProductRequest request);
}
