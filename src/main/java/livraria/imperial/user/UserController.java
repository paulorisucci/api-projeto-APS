package livraria.imperial.user;

import livraria.imperial.user.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping( value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        UserEntity userEntity = mapper.mapCreateRequestToEntity(createUserRequest);
        return mapper.mapEntityToResponse(service.create(userEntity));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers() {

        final var usersList = service.list();

        final var response = mapper.mapListToResponse(usersList);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("idUser") Integer idUser) {
        final var foundUser = service.find(idUser);

        final var response = mapper.mapEntityToResponse(foundUser);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{idUser}")
    public ResponseEntity<UserResponse> update(@PathVariable("idUser")  Integer idUser, @RequestBody @Valid PartialUpdateUserRequest request) {

        final var savedUser = service.partialUpdate(idUser, request);

        final var response = mapper.mapEntityToResponse(savedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable("idUser") Integer idUser) {

        service.delete(idUser);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        final var loggedUser = service.login(loginRequest);

        final var response = mapper.mapEntityToResponse(loggedUser);

        return ResponseEntity.ok(response);
    }
}
