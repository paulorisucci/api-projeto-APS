package livraria.imperial.product;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final UserService userService;

    private final List<Car> cars;

    private final ProductMapper mapper;


    private static class Messages{
        private final static String PRODUCT_ALREADY_EXISTS = "O produto já existe";

        private final static String PRODUCT_DOES_NOT_EXIST = "O produto não existe";

        private final static String USER_DOES_NOT_EXIST = "O usuário não existe";
    }

    public ProductEntity create(ProductRequest request) {
        verifyIfNameAlreadyExists(request);

        ProductEntity product = mapper.mapRequestToEntity(request);

        return productRepository.save(product);
    }

    public List<ProductEntity> list() {
        return productRepository.findAll();
    }

    public ProductEntity find(Integer idProduct) {
        return search(idProduct);
    }

    public ProductEntity update(ProductRequest request, Integer id) {
        verifyIfNameAlreadyExists(request);

        ProductEntity foundProduct = search(id);

        foundProduct.update(request);

        return productRepository.save(foundProduct);
    }

    public void delete(Integer idProduct) {
        productRepository.deleteById(idProduct);
    }

    private void verifyIfNameAlreadyExists(ProductRequest request) {
        if(productRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException(Messages.PRODUCT_ALREADY_EXISTS);
        }
    }

    private ProductEntity search(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Messages.PRODUCT_DOES_NOT_EXIST));
    }

    public Car addToCar(Integer idUser, Integer idProduct) {

        userService.verifyIfExistsById(idUser);
        ProductEntity foundProduct = search(idProduct);
        Car car = findCarById(idUser);
        car.addProductToCar(foundProduct);
        return car;
    }

    public Car findCar(Integer idUser) {
        return findCarById(idUser);
    }

    private Car findCarById(Integer idUser) {
        Optional<Car> foundCar = this.cars.stream().filter(car -> car.getIdUser().equals(idUser)).findFirst();

        if(foundCar.isEmpty())  {
            Car newCar = new Car(idUser, new LinkedList<>());
            this.cars.add(newCar);
            return newCar;
        }

        return foundCar.get();
    }

    public Car removeFromCar(Integer idUser, Integer idProduct) {
        Car foundCar = findCarById(idUser);

        foundCar.removeProduct(idProduct);

        return foundCar;
    }

    public void verifyIfProductExists(ProductEntity product) {
        if( !productRepository.exists(Example.of(product))) {
            throw new EntityNotFoundException(Messages.PRODUCT_DOES_NOT_EXIST+". Id: "+product.getId());
        }
    }

    public void clearCar(Integer idUser) {
        Car car = findCarById(idUser);
        car.clear();
        this.cars.remove(car);
    }
}
