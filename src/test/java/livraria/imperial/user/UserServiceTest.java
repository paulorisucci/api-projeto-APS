package livraria.imperial.user;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.factory.UserFactoryTest;
import livraria.imperial.user.dtos.UserEntity;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldCreateAnUserEntitySuccessfuly() {
        //Given
        UserEntity registerUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(registerUserRequest);

        UserEntity registeredUser = userService.create(registerUserRequest);

        //Then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(registeredUser.getCpf()).isEqualTo(registerUserRequest.getCpf());
            softly.assertThat(registeredUser.getEmail()).isEqualTo(registerUserRequest.getEmail());
            softly.assertThat(registeredUser.getLogin()).isEqualTo(registerUserRequest.getLogin());
            softly.assertThat(registeredUser.getName()).isEqualTo(registerUserRequest.getName());
            softly.assertThat(registeredUser.getAddress()).isEqualTo(registerUserRequest.getAddress());
            softly.assertThat(registeredUser.getPassword()).isEqualTo(registerUserRequest.getPassword());
        });
    }

    @Test
    public void createShouldThrowAnErrorWhenUserWithLoginAlreadyExists() {
        //Given
        UserEntity registerUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(true);

        //Then
        Assertions.assertThatThrownBy(() -> userService.create(registerUserRequest)).isInstanceOf(EntityAlreadyExistsException.class);
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));

    }

    @Test
    public void createShouldThrowAnErrorWhenUserWithCpfAlreadyExists() {
        //Given
        UserEntity registerUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(true);


        Assertions.assertThatThrownBy(() -> userService.create(registerUserRequest)).isInstanceOf(EntityAlreadyExistsException.class);
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));
    }

    @Test
    public void createShouldThrowAnErrorWhenUserWithEmailAlreadyExists() {
        //Given
        UserEntity registerUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(true);


        Assertions.assertThatThrownBy(() -> userService.create(registerUserRequest)).isInstanceOf(EntityAlreadyExistsException.class);
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));
    }

    @Test
    public void shouldUpdateAnUserSuccessfully() {
        //Given
        UserEntity updateUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(updateUserRequest);

        UserEntity updatedUser = userService.update(updateUserRequest);

        //Then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedUser.getId()).isEqualTo(updateUserRequest.getId());
            softly.assertThat(updatedUser.getCpf()).isEqualTo(updateUserRequest.getCpf());
            softly.assertThat(updatedUser.getEmail()).isEqualTo(updateUserRequest.getEmail());
            softly.assertThat(updatedUser.getLogin()).isEqualTo(updateUserRequest.getLogin());
            softly.assertThat(updatedUser.getName()).isEqualTo(updateUserRequest.getName());
            softly.assertThat(updatedUser.getAddress()).isEqualTo(updateUserRequest.getAddress());
            softly.assertThat(updatedUser.getPassword()).isEqualTo(updateUserRequest.getPassword());
        });
    }

    @Test
    public void updateShouldThrowAnErrorWhenUserWithIdDoesNotExist() {
        //Given
        UserEntity updateUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(false);

        //Then
        Assertions.assertThatThrownBy(() -> userService.update(updateUserRequest)).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));
    }

    @Test
    public void updateShouldThrowAnErrorWhenUserWithLoginAlreadyExist() {
        //Given
        UserEntity updateUserRequest = UserFactoryTest.createAValidUser();

        //When
        Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByCpf(Mockito.anyString())).thenReturn(false);

        //Then
        Assertions.assertThatThrownBy(() -> userService.update(updateUserRequest)).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));
    }
}
