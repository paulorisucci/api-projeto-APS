package livraria.imperial.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

    String login;

    String password;

    Boolean flagAdmin = Boolean.FALSE;

    public boolean isAdmin() {
        return this.flagAdmin.equals(Boolean.TRUE);
    }
}
