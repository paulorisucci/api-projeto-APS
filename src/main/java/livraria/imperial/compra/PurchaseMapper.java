package livraria.imperial.compra;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseResponse convertEntityToResponse(PurchaseEntity entity);

    List<PurchaseResponse> convertEntityListToResponse(List<PurchaseEntity> entityList);

}
