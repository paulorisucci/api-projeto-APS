package livraria.imperial.formasDePagamento;

import livraria.imperial.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    PaymentRepository repository;

    private static class Messages {
        private final static String PAYMENT_DOES_NOT_EXISTS = "Forma de pagamento não encontrada";
    }

    public List<PaymentEntity> list() {
        return repository.findAll();
    }

    public PaymentEntity find(Integer id) {
        return findById(id);
    }

    public PaymentEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Messages.PAYMENT_DOES_NOT_EXISTS));
    }

    public void verifyIfExistsById(Integer id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException(Messages.PAYMENT_DOES_NOT_EXISTS);
        }
    }
}
