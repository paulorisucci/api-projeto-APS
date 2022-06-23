package livraria.imperial.user.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String name;

    private String cpf;

    private String email;

    private String login;
}