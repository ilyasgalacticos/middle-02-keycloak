package kz.bitlab.middle02.keycloakservice.api;

import kz.bitlab.middle02.keycloakservice.converter.UserUtils;
import kz.bitlab.middle02.keycloakservice.dto.UserChangePasswordDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserCreateDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserDTO;
import kz.bitlab.middle02.keycloakservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return new ResponseEntity<>(userService.createUser(userCreateDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/current-user-name")
    public String getCurrentUserName() {
        return UserUtils.getCurrentUserName();
    }

    @GetMapping(value = "/current-user")
    public UserDTO getCurrentUser() {
        return UserUtils.getCurrentUser();
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO) {

        String userName = UserUtils.getCurrentUserName();
        if (userName == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Couldn't find user");
        }

        try{

            userService.changePassword(userName, userChangePasswordDTO.getNewPassword());
            return ResponseEntity.ok("Password changed successfully!");

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on changing password");
        }

    }

}