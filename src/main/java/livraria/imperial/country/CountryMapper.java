package livraria.imperial.country;

import livraria.imperial.country.dtos.CountryEntity;
import livraria.imperial.country.dtos.CountryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    public CountryEntity mapRequestToEntity(CountryRequest createCountryRequest);
}
