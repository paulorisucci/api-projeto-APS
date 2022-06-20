package livraria.imperial.controllers;

import livraria.imperial.domain.model.dtos.UserLoginRequest;
import livraria.imperial.domain.model.entities.User;
import livraria.imperial.domain.model.responses.UserProjection;
import livraria.imperial.domain.model.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping( value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserProjection>> listUsers() {


        return  ResponseEntity.ok(service.listUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<UserProjection> login(@RequestBody UserLoginRequest loginRequest) {
        User loggedUser = service.login(loginRequest);

        return ResponseEntity.ok(userResponse.mapFromUserToResponse(loggedUser));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserProjection> getUser(@PathParam("idUser") Integer idUser) {
        User foundUser = service.getUser(idUser);

        return ResponseEntity.ok(userResponse.mapFromUserToResponse(foundUser));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserProjection> update(@PathParam("idUser") Integer idUser, @RequestBody User user) {

        user.setId(idUser);

        User savedUser = service.saveUser(user);

        return ResponseEntity.ok();
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity delete(@PathParam("idUser") Integer idUser) {

        service.deleteUser(idUser);

        return (ResponseEntity) ResponseEntity.noContent();
    }
}
