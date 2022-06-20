package livraria.imperial.domain.model.services;

import com.google.common.hash.Hashing;
import livraria.imperial.domain.model.dtos.UserLoginRequest;
import livraria.imperial.domain.model.entities.User;
import livraria.imperial.domain.model.repositories.UserRepository;
import livraria.imperial.domain.model.responses.UserProjection;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
        return repository.save(user);
    }

    public List<UserProjection> listUsers() {
        return repository.findAllResponse();
    }

    public User getUser(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteUser(Integer id) {
        User deletedUser = repository.findById(id).orElse(null);
        if(deletedUser != null) {
            repository.delete(deletedUser);
        }
    }

    public UserProjection login(UserLoginRequest loginRequest) {
        loginRequest.setPassword(Hashing.sha256().hashString(loginRequest.getPassword(), StandardCharsets.UTF_8).toString());

        return repository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword()).orElse(null);
    }
}
