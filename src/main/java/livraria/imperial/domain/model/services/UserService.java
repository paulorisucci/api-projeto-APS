package livraria.imperial.domain.model.services;

import livraria.imperial.domain.model.dtos.LoginRequest;
import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.repositories.AdminRepository;
import livraria.imperial.domain.model.repositories.UserRepository;
import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.exceptions.LoginFailedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    private final String USER_NOT_FOUND = "Usuário não encontrado";

    private final String LOGIN_FAILED_FOR_USER = "O login falhou para as credenciais. Login ou senha incorretos";

    private final String LOGIN_FAILED_FOR_ADMIN = "O login falhou para as credenciais. Login ou senha incorretos";

    private final String USER_WITH_EMAIL_ALREADY_EXISTS = "O email informado já está cadastrado";

    private static final String USER_WITH_CPF_ALREADY_EXISTS = "O cpf informado é inválido ou já existe";



    public UserService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public UserEntity create(UserEntity user) {
        verifyIfUserWithLoginAlreadyExists(user.getLogin());
        verifyIfUserWithCpfAlreadyExists(user.getCpf());
        verifyIfUserWithEmailAlreadyExists(user.getEmail());
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        verifyIfUserExists(user.getId());
        verifyIfUserWithLoginAlreadyExists(user.getLogin());
        verifyIfUserWithCpfAlreadyExists(user.getCpf());
        verifyIfUserWithEmailAlreadyExists(user.getEmail());
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
        deletedUser.ifPresent( user -> userRepository.delete(user));
    }

    public UserEntity login(LoginRequest loginRequest) {
        return loginRequest.isAdmin() ? searchAdminForLogin(loginRequest) : searchUserForLogin(loginRequest);
    }

    public UserEntity searchUserForLogin(LoginRequest loginRequest) {
        return userRepository.findByLoginAndPassword(loginRequest.getLogin(),
                loginRequest.getPassword()).orElseThrow(() -> new LoginFailedException(LOGIN_FAILED_FOR_USER));
    }

    public UserEntity searchAdminForLogin(LoginRequest loginRequest) {
        return adminRepository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword())
                .orElseThrow(() -> new LoginFailedException(LOGIN_FAILED_FOR_ADMIN));
    }

    public UserEntity searchForUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    public void verifyIfUserExists(Integer id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
    }

    public void verifyIfUserWithEmailAlreadyExists(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException(USER_WITH_EMAIL_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithCpfAlreadyExists(String cpf) {
        if(userRepository.existsByCpf(cpf)) {
            throw new EntityAlreadyExistsException(USER_WITH_CPF_ALREADY_EXISTS);
        }
    }

    public void verifyIfUserWithLoginAlreadyExists(String login) {
        if(userRepository.existsByLogin(login)) {
            throw new EntityAlreadyExistsException(USER_WITH_CPF_ALREADY_EXISTS);
        }
    }
}
