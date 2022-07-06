package livraria.imperial.address;

import livraria.imperial.address.dto.AddressEntity;
import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    private static class Messages {
        public final static String ADDRESS_NOT_FOUND = "Endereco não encontrado";

        public final static String ADDRESS_ALREADY_EXISTS = "Endereco já existe";

    }

    public AddressEntity create(AddressEntity address) {
        verifyIfAddressAlreadyExist(address);
        return saveAddress(address);
    }

    public AddressEntity update(AddressEntity address) {
        verifyIfAddressExists(address.getId());
        verifyIfAddressAlreadyExist(address);
        return saveAddress(address);
    }

    private AddressEntity saveAddress(AddressEntity address) {
        AddressEntity savedAddress = repository.save(address);
        repository.refresh(savedAddress);
        return savedAddress;
    }

    public List<AddressEntity> list() {
        return repository.findAll();
    }

    public AddressEntity find(Integer id) {
        return searchAddressById(id);
    }

    private AddressEntity searchAddressById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Messages.ADDRESS_NOT_FOUND));
    }

    private void verifyIfAddressExists(Integer id) {
         if(!repository.existsById(id)) {
             throw new EntityNotFoundException(Messages.ADDRESS_NOT_FOUND);
         }
    }

    private void verifyIfAddressAlreadyExist(AddressEntity address) {
        Optional<AddressEntity> optionalAddress = repository.findOne(address.getBusinessKeyExample());

        if(optionalAddress.isEmpty()) {
            return;
        }

        if(!optionalAddress.get().equals(address))  {
            throw new EntityAlreadyExistsException(Messages.ADDRESS_ALREADY_EXISTS);
        }
    }

    public void delete(Integer id) {
        final var deletedAddress = repository.findById(id);

        deletedAddress.ifPresent(
                address -> repository.delete(address)
        );
    }
}
