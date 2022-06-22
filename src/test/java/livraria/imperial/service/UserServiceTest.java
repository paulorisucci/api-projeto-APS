package livraria.imperial.service;

import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.repositories.UserRepository;
import livraria.imperial.domain.model.services.UserService;
import livraria.imperial.factory.UserFactoryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Test
    public void mustRegisterTheUserSuccessfully() {
        //Given
        final var user = UserFactoryTest.createAValidUser();
        final int NUMBER_OF_CALLS = 1;

        //When
        Mockito.when(userService.save(Mockito.any(UserEntity.class))).thenReturn(user);

        //Then
        userService.save(user);

        Mockito.verify(repository, Mockito.times(NUMBER_OF_CALLS)).save(Mockito.any(UserEntity.class));
    }

    @Test
    public void mustListTheUsersSuccessfully() {
        //Given
        final int LIST_SIZE = 3;
        final int NUMBER_OF_CALLS = 1;
        final var userList = UserFactoryTest.createAnUserList(LIST_SIZE);

        //When
        Mockito.when(repository.findAll()).thenReturn(userList);

        //Then
        var resultList = userService.list();

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(resultList.size()).isEqualTo(LIST_SIZE);
                    Mockito.verify(repository, Mockito.times(NUMBER_OF_CALLS)).findAll();
                }
        );
    }

}
