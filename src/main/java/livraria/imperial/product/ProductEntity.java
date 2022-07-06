package livraria.imperial.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import livraria.imperial.book.BookEntity;
import livraria.imperial.compra.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {"id"})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "preco")
    private BigDecimal price;

    @Column(name = "quantidadeEstoque")
    private Integer quantityInStock;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<PurchaseEntity> purchaseList;

    public void update(ProductRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantityInStock = request.getQuantityInStock();
    }

    @JsonIgnore
    protected ExampleMatcher getProductsBusinessKeyMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("price", "quantityInStock");
    }

    @JsonIgnore
    public Example<ProductEntity> getProductsBusinessKey() {
        return Example.of(this, this.getProductsBusinessKeyMatcher());
    }

    public boolean isEQualByProductssBusinesskey(ProductEntity anotherProduct) {
        return this.getProductsBusinessKey().equals(anotherProduct.getProductsBusinessKey())
                && Objects.nonNull(anotherProduct.getId()) && !Objects.equals(this.id, anotherProduct.getId());
    }
}
