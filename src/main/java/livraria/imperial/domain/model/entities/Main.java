package livraria.imperial.domain.model.entities;

import livraria.imperial.DemoApplication;
import livraria.imperial.domain.model.repositories.UserRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {



    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(DemoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        UserRepository repository = applicationContext.getBean(UserRepository.class);

        User user = new User();

        user.setName("Teste");
        user.setEmail("teste@teste.com");
        user.setPassword("test");
        user.setLogin("test");

//        User savedUser = repository.saveUser(user);

//        System.out.println(savedUser);
    }
}
