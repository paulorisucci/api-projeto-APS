package livraria.imperial.domain.model.services;

import livraria.imperial.domain.model.dtos.LoginRequest;
import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.repositories.AdminRepository;
import livraria.imperial.domain.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    public UserService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> list() {
        return userRepository.findAll();
    }

    public UserEntity find(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        final var deletedUser = userRepository.findById(id);
        deletedUser.ifPresent( user -> userRepository.delete(user));
    }

    public UserEntity login(LoginRequest loginRequest) {
        return loginRequest.isAdmin() ? searchAdminForLogin(loginRequest) : searchUserForLogin(loginRequest);
    }

    public UserEntity searchUserForLogin(LoginRequest loginRequest) {
        return userRepository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword()).orElse(null);
    }

    public UserEntity searchAdminForLogin(LoginRequest loginRequest) {
        return adminRepository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword()).orElse(null);
    }
}
