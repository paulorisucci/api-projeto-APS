package livraria.imperial.product;

import livraria.imperial.user.dtos.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return service.create(productRequest);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listUsers() {

        final var productList = service.list();


        return  ResponseEntity.ok(productList);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductEntity> getUser(@PathVariable("idProduct") Integer idProduct) {
        final var foundProduct = service.find(idProduct);

        return ResponseEntity.ok(foundProduct);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductEntity> update(@PathVariable("idProduct")  Integer idProduct, @RequestBody @Valid ProductRequest request) {

        final var savedProduct = service.update(request, idProduct);

        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable("idProduct") Integer idProduct) {

        service.delete(idProduct);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idProduct}/car/{idUser}")
    public ResponseEntity<Car> addToCar(@PathVariable Integer idUser, @PathVariable Integer idProduct) {
        return ResponseEntity.ok(service.addToCar(idUser, idProduct));
    }

    @GetMapping("/car/{idUser}")
    public ResponseEntity<Car> findCar(@PathVariable Integer idUser) {
        return ResponseEntity.ok(service.findCar(idUser));
    }

    @PostMapping("/{idProduct}/car/{idUser}/remove")
    public ResponseEntity<Car> removeFromCar(@PathVariable Integer idUser, @PathVariable Integer idProduct) {
        return ResponseEntity.ok(service.removeFromCar(idUser, idProduct));
    }

    @PostMapping("/car/{idUser}/clear")
    public ResponseEntity<Void> clearCar(@PathVariable Integer idUser) {
        service.clearCar(idUser);
        return ResponseEntity.noContent().build();
    }
}
