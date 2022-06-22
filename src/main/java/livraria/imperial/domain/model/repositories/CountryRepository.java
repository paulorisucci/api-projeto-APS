package livraria.imperial.domain.model.repositories;

import livraria.imperial.domain.model.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
