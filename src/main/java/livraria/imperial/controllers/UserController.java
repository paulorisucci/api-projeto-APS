package livraria.imperial.controllers;

import livraria.imperial.domain.model.dtos.LoginRequest;
import livraria.imperial.domain.model.entities.UserEntity;
import livraria.imperial.domain.model.mappers.UserMapper;
import livraria.imperial.domain.model.responses.UserResponse;
import livraria.imperial.domain.model.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public UserResponse createUser(@RequestBody UserEntity user) {

        var response = mapper.mapEntityToResponse(service.save(user));
        return response;
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

    @PutMapping("/{idUser}")
    public ResponseEntity<UserResponse> update(@PathVariable("idUser") Integer idUser, @RequestBody UserEntity user) {

        user.setId(idUser);

        final var savedUser = service.save(user);

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
