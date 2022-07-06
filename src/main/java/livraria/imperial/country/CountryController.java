package livraria.imperial.country;


import livraria.imperial.country.dtos.CountryEntity;
import livraria.imperial.country.dtos.CountryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping( value = "/countries", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CountryController {

    private final CountryService service;
    private final CountryMapper mapper;

    private static class Paths{
        private static final String PATH_ID_COUNTRY = "/{idCountry}";
    }

    public CountryController(CountryService service, CountryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryEntity createCountry(@RequestBody @Valid CountryRequest request) {
        CountryEntity country = mapper.mapRequestToEntity(request);
        return service.create(country);
    }

    @GetMapping(Paths.PATH_ID_COUNTRY)
    public ResponseEntity<CountryEntity> findCountry(@PathVariable("idCountry") Integer idCountry) {
        return ResponseEntity.ok(service.find(idCountry));
    }

    @GetMapping
    public ResponseEntity<List<CountryEntity>> listCountries() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping(Paths.PATH_ID_COUNTRY)
    public ResponseEntity<CountryEntity> updateCountry(@PathVariable("idCountry") Integer id,
                                                       @RequestBody @Valid CountryRequest request) {

        CountryEntity country = mapper.mapRequestToEntity(request);
        country.setId(id);
        return ResponseEntity.ok(service.update(country));
    }

    @DeleteMapping(Paths.PATH_ID_COUNTRY)
    public ResponseEntity<Void> deleteCountry(@PathVariable("idCountry") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
