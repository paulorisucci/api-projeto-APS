package livraria.imperial.address;


import livraria.imperial.address.dto.AddressEntity;
import livraria.imperial.address.dto.AddressRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping( value = "/addresses", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {

    private AddressService service;

    private AddressMapper mapper;

    private static class Paths  {
        private final static String PATH_ID_ADDRESS = "/{idAddress}";
    }


    public AddressController(AddressService service, AddressMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AddressEntity createAddress(@RequestBody @Valid AddressRequest request) {

        AddressEntity address = mapper.mapRequestToEntity(request);

        return service.create(address);
    }

    @GetMapping
    public ResponseEntity<List<AddressEntity>> listAdresses() {

        return ResponseEntity.ok(service.list());
    }

    @GetMapping(Paths.PATH_ID_ADDRESS)
    public ResponseEntity<AddressEntity> getAddress(@PathVariable Integer idAddress) {
        return ResponseEntity.ok(service.find(idAddress));
    }

    @DeleteMapping(Paths.PATH_ID_ADDRESS)
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer idAddress) {
        service.delete(idAddress);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(Paths.PATH_ID_ADDRESS)
    public ResponseEntity<AddressEntity> update(@PathVariable Integer idAddress, @RequestBody @Valid AddressRequest request) {
        AddressEntity address = mapper.mapRequestToEntity(request);
        address.setId(idAddress);
        return ResponseEntity.ok(service.update(address));
    }
}
