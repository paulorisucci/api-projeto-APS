package livraria.imperial.user.dtos;

import livraria.imperial.address.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {

    @NotNull
    @Size(min = 2, max = 100, message = "{user.name.size}")
    private String name;

    @Size(min = 11, max = 11, message = "{cpf.size}")
    private String cpf;

    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{user.email.format}")
    private String email;

    @NotNull(message = "#{'${campo.notnull}'.replace('{}', 'login')")
    private String login;

    @NotNull(message = "#{'${campo.notnull}'.replace('{}', 'password')")
    private String password;

    private AddressEntity address;

}
