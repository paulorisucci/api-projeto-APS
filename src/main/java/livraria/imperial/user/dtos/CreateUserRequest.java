package livraria.imperial.user.dtos;

import com.google.common.hash.Hashing;
import livraria.imperial.address.dto.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {

    @NotNull(message = "{user.name.notnull}")
    @Size(min = 2, max = 100, message = "{user.name.size}")
    private String name;

    @Size(min = 11, max = 11, message = "{user.cpf.size}")
    private String cpf;

    @NotEmpty(message = "{user.email.notempty}")
    @Email(message = "{user.email.format}")
    private String email;

    @NotNull(message = "{user.login.notnull}")
    private String login;

    @NotNull(message = "{user.password.notnull}")
    private String password;

//    private AddressEntity address;

    private String encryptEntry(String entry) {
        return Hashing.sha256()
                .hashString(entry, StandardCharsets.UTF_8)
                .toString();
    }

    public void setPassword(String password) {
        this.password = encryptEntry(password);
    }
}
