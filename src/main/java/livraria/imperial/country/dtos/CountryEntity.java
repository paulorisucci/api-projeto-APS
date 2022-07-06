package livraria.imperial.country.dtos;

import lombok.*;

import javax.persistence.*;

@Entity(name = "pais")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Integer id;

    @Column(name = "nome")
    private String name;
}
