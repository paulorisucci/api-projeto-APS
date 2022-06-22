package livraria.imperial.domain.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity(name = "endereco")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressEntity {

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

}
