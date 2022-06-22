package livraria.imperial.controllers;


import livraria.imperial.domain.model.entities.CountryEntity;
import livraria.imperial.domain.model.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( value = "/countries", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CountryController {

    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryEntity createCountry(@RequestBody CountryEntity entity) {
        return service.save(entity);
    }

    @GetMapping("/{idCountry}")
    public ResponseEntity<CountryEntity> findCountry(@PathVariable("idCountry") Integer idCountry) {
        return ResponseEntity.ok(service.find(idCountry));
    }

    @GetMapping
    public ResponseEntity<List<CountryEntity>> listCountries() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping("/{idCountry}")
    public ResponseEntity<CountryEntity> updateCountry(@PathVariable("idCountry") Integer id,
                                                       @RequestBody CountryEntity entity) {
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{idCountry}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("idCountry") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
