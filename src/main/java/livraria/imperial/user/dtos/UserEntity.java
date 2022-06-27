package livraria.imperial.user.dtos;

import javax.persistence.*;
import com.google.common.hash.Hashing;
import livraria.imperial.address.AddressEntity;
import lombok.*;

import java.nio.charset.StandardCharsets;

@Entity(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private AddressEntity address;

}
