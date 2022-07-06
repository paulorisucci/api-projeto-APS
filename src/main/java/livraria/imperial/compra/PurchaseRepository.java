package livraria.imperial.compra;

import livraria.imperial.customRepositories.CustomRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PurchaseRepository extends CustomRepository<PurchaseEntity, Integer> {

    @Query(value = "SELECT SUM(value) FROM PurchaseEntity")
    public BigDecimal sumValor();
}
