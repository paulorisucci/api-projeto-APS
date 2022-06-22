package livraria.imperial.controller;


import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.services.UserService;
import livraria.imperial.factory.UserFactoryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;


public class UserControllerTest {

    protected MockMvc mockMvc;

    private UserService userService;


}
