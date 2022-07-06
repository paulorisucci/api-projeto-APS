package livraria.imperial.user;

import livraria.imperial.address.AddressRepository;
import livraria.imperial.user.dtos.LoginRequest;
import livraria.imperial.user.admin.AdminRepository;
import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.exceptions.LoginFailedException;
import livraria.imperial.user.dtos.PartialUpdateUserRequest;
import livraria.imperial.user.dtos.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    private final AddressRepository addressRepository;

    private static class Messages {

        private final static String USER_NOT_FOUND = "Usuário não encontrado";

        private final static String LOGIN_FAILED_FOR_USER = "O login falhou para as credenciais. Login ou senha incorretos";

        private final static String LOGIN_FAILED_FOR_ADMIN = "O login falhou para as credenciais. Login ou senha incorretos";

        private final static String USER_WITH_EMAIL_ALREADY_EXISTS = "O email informado já está cadastrado";

        private static final String USER_WITH_CPF_ALREADY_EXISTS = "O cpf informado é inválido ou já existe";

        private static final String ADDRESS_DOES_NOT_EXIST = "O endereço informado não existe";

    }

    public UserEntity create(UserEntity user) {
        verifyIfUserWithLoginAlreadyExists(user);
        verifyIfUserWithCpfAlreadyExists(user);
        verifyIfUserWithEmailAlreadyExists(user);
        return userRepository.save(user);
    }

    public UserEntity partialUpdate(Integer idUser, PartialUpdateUserRequest updateRequest) {
        UserEntity user = find(idUser);
        verifyIfUserWithLoginAndWithoutIdAlreadyExists(user);
        verifyIfUserWithCpfAndWithoutIdAlreadyExists(user);
        verifyIfUserWithEmailAndWithoutIdAlreadyExists(user);
        user.partialUpdate(updateRequest);
        return userRepository.save(user);
    }

    public List<UserEntity> list() {
        return userRepository.findAll();
    }

    public UserEntity find(Integer id) {
        return searchForUserById(id);
    }

    public void delete(Integer id) {
        final var deletedUser = userRepository.findById(id);
        deletedUser.ifPresent(userRepository::delete);
    }

    public UserEntity login(LoginRequest loginRequest) {
        return loginRequest.isAdmin() ? searchAdminForLogin(loginRequest) : searchUserForLogin(loginRequest);
    }

    public UserEntity searchUserForLogin(LoginRequest loginRequest) {
        return userRepository.findByLoginAndPassword(loginRequest.getLogin(),
                loginRequest.getPassword()).orElseThrow(() -> new LoginFailedException(Messages.LOGIN_FAILED_FOR_USER));
    }

    public UserEntity searchAdminForLogin(LoginRequest loginRequest) {
        return adminRepository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword())
                .orElseThrow(() -> new LoginFailedException(Messages.LOGIN_FAILED_FOR_ADMIN));
    }

    public UserEntity searchForUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Messages.USER_NOT_FOUND));
    }

    public void verifyIfUserWithEmailAndWithoutIdAlreadyExists(UserEntity user) {
        if(userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_EMAIL_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithCpfAndWithoutIdAlreadyExists(UserEntity user) {
        if(userRepository.existsByCpfAndIdNot(user.getCpf(), user.getId())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_CPF_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithLoginAndWithoutIdAlreadyExists(UserEntity user) {
        if(userRepository.existsByLoginAndIdNot(user.getLogin(), user.getId())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_CPF_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithEmailAlreadyExists(UserEntity user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_EMAIL_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithCpfAlreadyExists(UserEntity user) {
        if(userRepository.existsByCpf(user.getCpf())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_CPF_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithLoginAlreadyExists(UserEntity user) {
        if(userRepository.existsByLogin(user.getLogin())) {
            throw new EntityAlreadyExistsException(Messages.USER_WITH_CPF_ALREADY_EXISTS);
        }
    }

    public void verifyIfExistsById(Integer id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException(Messages.USER_NOT_FOUND);
        }
    }
}
