package livraria.imperial.country;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository countryRepository;

    private static class CountryServiceMessages {

        private static final String COUNTRY_DOES_NOT_EXIST = "Não existe um país cadastrado com esse id";
        private static final String COUNTRY_ALREADY_EXISTS = "Já existe um país cadastrado com nome {}";
    }

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public CountryEntity create(CountryEntity country) {
        verifyIfCountryAlreadyExistsByName(country);
        return save(country);
    }

    public CountryEntity update(CountryEntity country) {
        verifyIfCountryExistsById(country);
        return save(country);
    }

    public CountryEntity find(Integer id) {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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

    public void verifyIfCountryExistsById(CountryEntity country) {
        if(!countryRepository.existsById(country.getId())) {
            throw new EntityNotFoundException(CountryServiceMessages.COUNTRY_DOES_NOT_EXIST);
        }
    }

    private void verifyIfCountryAlreadyExistsByName(CountryEntity country) {
        if(countryRepository.existsByName(country.getName())) {
            throw new EntityAlreadyExistsException(CountryServiceMessages.COUNTRY_ALREADY_EXISTS.replace("{}", country.getName()));
        }
    }

    public CountryEntity save(CountryEntity country) {
        return countryRepository.save(country);
    }
}
