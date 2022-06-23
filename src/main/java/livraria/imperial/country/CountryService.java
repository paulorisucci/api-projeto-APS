package livraria.imperial.country;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository countryRepository;

    private static class CountryServiceMessages {
        private static final String COUNTRY_ALREADY_EXISTS = "Given country already exists in database";
    }

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public CountryEntity create(CountryEntity country) {
        verifyIfCountryAlreadyExists(country);
        return countryRepository.save(country);
    }

    public CountryEntity find(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    public List<CountryEntity> list() {
        return countryRepository.findAll();
    }

    public void delete(Integer id) {
        final var country = countryRepository.findById(id);

        country.ifPresent(
                countryEntity -> countryRepository.delete(countryEntity)
        );
    }



    private void verifyIfCountryAlreadyExists(CountryEntity country) {
        if(countryRepository.existsByName()) {
            throw new EntityAlreadyExistsException(CountryServiceMessages.COUNTRY_ALREADY_EXISTS);
        }
    }
}
