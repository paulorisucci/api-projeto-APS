package livraria.imperial.user;


import livraria.imperial.factory.UserFactoryTest;
import livraria.imperial.user.UserController;
import livraria.imperial.user.UserService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import livraria.imperial.user.dtos.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserController.class, UserService.class})
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    private static class Paths {
        private static String PATH_USER = "/users";
    }

    @Test
    public void shouldRegisterAUser() throws Exception{
        //Given
        UserEntity createdUser = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userService.create(Mockito.any(UserEntity.class))).thenReturn(createdUser);

        //Then
        mockMvc.perform(MockMvcRequestBuilders.post(Paths.PATH_USER)
                .content(String.valueOf(createdUser))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldThrowAnErrorWhenLoginAlreadyExists() {

    }
}
