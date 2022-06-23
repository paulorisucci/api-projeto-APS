package livraria.imperial.user.admin;


import livraria.imperial.user.dtos.UserEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminEntity extends UserEntity {

    @Column(name = "cargo_na_empresa")
    private String role;
}
