package livraria.imperial.user.dtos;

import javax.persistence.*;

import livraria.imperial.address.dto.AddressEntity;
import lombok.*;

import java.util.Objects;

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

    public void partialUpdate(PartialUpdateUserRequest request) {
        if(Objects.nonNull(request.getCpf())) {
            this.cpf = request.getCpf();
        }

        if(Objects.nonNull(request.getEmail())) {
            this.email = request.getEmail();
        }

        if(Objects.nonNull(request.getLogin())) {
            this.login = request.getLogin();
        }

        if(Objects.nonNull(request.getPassword())) {
            this.password = request.getPassword();
        }

        if(Objects.nonNull(request.getName())) {
            this.name = request.getName();
        }

        if(Objects.nonNull(request.getAddress())) {
            this.address = request.getAddress();
        }
    }
}
