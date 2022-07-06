package livraria.imperial.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of="idUser")
public class Car {

    private Integer idUser;

    private List<ProductEntity> products;


    public ProductEntity addProductToCar(ProductEntity product) {
        this.products.add(product);
        return product;
    }

    public void removeProduct(Integer idProduct) {
        this.products.stream().filter(product -> product.getId().equals(idProduct)).findFirst()
                .ifPresent(product -> products.remove(product));
    }

    public BigDecimal getTotalValue() {
        BigDecimal total = BigDecimal.ZERO;

        for(ProductEntity product : this.products) {
            total = total.add(product.getPrice());
        }
        return total;
    }

    public void clear() {
        this.products = null;
    }
}
