package livraria.imperial.country;

import livraria.imperial.country.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    boolean existsByName();
}
