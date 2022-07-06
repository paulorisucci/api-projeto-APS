package livraria.imperial.compra;

import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.formasDePagamento.PaymentService;
import livraria.imperial.product.ProductEntity;
import livraria.imperial.product.ProductService;
import livraria.imperial.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseService {

    private PurchaseRepository repository;
    private PaymentService formasDePagamentoService;
    private UserService userService;

    private ProductService productService;

    private static class Messages{
        private static final String PURCHASE_DOES_NOT_EXIST = "O id da compra não foi encontrado";
    }

    public PurchaseEntity create(PurchaseEntity purchase) {
        formasDePagamentoService.verifyIfExistsById(purchase.getPayment().getId());
        userService.verifyIfExistsById(purchase.getUser().getId());
        this.verifyIfProductListExist(purchase.getProductList());
        return savePurchase(purchase);
    }

    private void verifyIfProductListExist(List<ProductEntity> productList) {
        productList.forEach(product -> productService.verifyIfProductExists(product));
    }

    public List<PurchaseEntity> list(PurchaseEntity purchase) {
        return repository.findAll(Example.of(purchase, ExampleMatcher.matching()));
    }

    public PurchaseEntity update(PurchaseEntity purchase) {
        this.verifyIfExistsById(purchase.getId());
        formasDePagamentoService.verifyIfExistsById(purchase.getPayment().getId());
        userService.verifyIfExistsById(purchase.getUser().getId());
        this.verifyIfProductListExist(purchase.getProductList());
        return repository.save(purchase);
    }

    public PurchaseEntity find(Integer id) {
        return findById(id);
    }

    private void verifyIfExistsById(Integer id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException(Messages.PURCHASE_DOES_NOT_EXIST);
        }
    }

    private PurchaseEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Messages.PURCHASE_DOES_NOT_EXIST));
    }

    private PurchaseEntity savePurchase(PurchaseEntity purchase) {
        final var savedPurchase = repository.save(purchase);

        repository.refresh(savedPurchase);

        return savedPurchase;
    }
    public ProfitResponse getTotalProfit() {
        ProfitResponse profitResponse = new ProfitResponse();
        profitResponse.setTotalValue(repository.sumValor());
        return profitResponse;
    }


}
