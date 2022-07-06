package livraria.imperial.compra;

import livraria.imperial.formasDePagamento.PaymentEntity;
import livraria.imperial.product.ProductEntity;
import livraria.imperial.user.dtos.UserEntity;
import livraria.imperial.user.dtos.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class PurchaseResponse {

    private Integer id;

    private LocalDateTime date;

    private BigDecimal value;

    private UserResponse user;

    private PaymentEntity payment;

    private List<ProductEntity> productList;
}
