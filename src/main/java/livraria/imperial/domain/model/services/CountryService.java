package livraria.imperial.domain.model.services;

import livraria.imperial.domain.model.entities.CountryEntity;
import livraria.imperial.domain.model.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository repository;

    public CountryService(CountryRepository repository){
        this.repository = repository;
    }

    public CountryEntity save(CountryEntity entity) {
        return repository.save(entity);
    }

    public CountryEntity find(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<CountryEntity> list() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        final var country = repository.findById(id);

        country.ifPresent(
                countryEntity -> repository.delete(countryEntity)
        );
    }
}
