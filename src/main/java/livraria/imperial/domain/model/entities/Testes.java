package livraria.imperial.domain.model.entities;

import livraria.imperial.DemoApplication;
import livraria.imperial.domain.model.repositories.AdminRepository;
import livraria.imperial.domain.model.repositories.UserRepository;
import livraria.imperial.domain.model.responses.UserResponse;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Testes {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(DemoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        AdminRepository repository = applicationContext.getBean(AdminRepository.class);

        var adminEntity = new AdminEntity();
        adminEntity.setId(10);
        adminEntity.setEmail("teste@teste1531.com");
        adminEntity.setCpf("99999999999");
        adminEntity.setName("Zezin da boca upgrated");
        adminEntity.setLogin("zezin.da.boca.upgrated.com");
        adminEntity.setRole("CEO");
        adminEntity.setPassword("Z3z1nD@M@c0nH4!Up!Gr4t3d");

        System.out.println(repository.save(adminEntity));

        UserEntity entity  = UserEntity.builder()
                .id(1)
                .name("shalala")
                .login("shalala")
                .cpf("shalala")
                .password("shalala")
                .email("shalala")
                .build();

//        UserEntity user = new UserEntity();

//        Optional<UserEntity> entity = repository.findById(1);
//
//        System.out.println(entity);
//        user.setName("Teste");
//        user.setEmail("teste@teste.com");
//        user.setPassword("test");
//        user.setLogin("test");
//
//        UserResponse response = new UserResponse();
//        response.setId(user.getId());


//        User savedUser = repository.saveUser(user);

//        System.out.println(savedUser);
    }
}
