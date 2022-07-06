package livraria.imperial.compra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import livraria.imperial.formasDePagamento.PaymentEntity;
import livraria.imperial.product.ProductEntity;
import livraria.imperial.user.dtos.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"id_compra"})
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer id;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "valor")
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_formadepagamento")
    private PaymentEntity payment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="Item_Compra",
            joinColumns = @JoinColumn(name = "id_compra"),
            inverseJoinColumns = @JoinColumn(name="id_produto")
    )
    private List<ProductEntity> productList;

}
