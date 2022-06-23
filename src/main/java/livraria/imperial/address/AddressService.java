package livraria.imperial.address;

import livraria.imperial.address.AddressEntity;
import livraria.imperial.address.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressEntity save(AddressEntity address) {
        AddressEntity createdAddress = repository.save(address);
        repository.refresh(createdAddress);

        return createdAddress;
    }

    public List<AddressEntity> list() {
        return repository.findAll();
    }

    public AddressEntity find(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        final var deletedAddress = repository.findById(id);

        deletedAddress.ifPresent(
                address -> repository.delete(address)
        );
    }
}
