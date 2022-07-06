package livraria.imperial.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {

    @Size(min=1, max=50, message = "{product.name.size}")
    @NotNull(message = "{product.name.notnull}")
    protected String name;

    @PositiveOrZero(message = "{product.price.positiveOrZero}")
    @NotNull(message = "{product.price.notnull}")
    protected BigDecimal price;

    @PositiveOrZero(message = "{product.quantidadeEstoque.positiveOrZero}")
    @NotNull(message = "{product.quantityInStock.notnull}")
    protected Integer quantityInStock;
}
