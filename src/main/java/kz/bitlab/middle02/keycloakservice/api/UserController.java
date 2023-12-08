package kz.bitlab.middle02.keycloakservice.api;

import kz.bitlab.middle02.keycloakservice.dto.UserCreateDTO;
import kz.bitlab.middle02.keycloakservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(userService.createUser(userCreateDTO), HttpStatus.OK);
    }

}