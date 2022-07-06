package livraria.imperial.address.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import livraria.imperial.country.dtos.CountryEntity;
import lombok.*;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "endereco")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8110410999216407659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    private CountryEntity country;

    @Column(name="estado")
    private String state;

    @Column(name="cidade")
    private String city;

    @Column(name="bairro")
    private String district;

    @Column(name="cep")
    private String cep;

    @Column(name="numero")
    private String number;

    @Column(name="complemento")
    private String complement;

    private ExampleMatcher getBusinessKeyMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("id");
    }

    @JsonIgnore
    public Example<AddressEntity> getBusinessKeyExample() {
        return Example.of(this, this.getBusinessKeyMatcher());
    }

}
