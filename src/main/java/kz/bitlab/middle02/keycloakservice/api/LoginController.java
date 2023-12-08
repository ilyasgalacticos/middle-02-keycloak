package kz.bitlab.middle02.keycloakservice.api;

import kz.bitlab.middle02.keycloakservice.dto.UserLoginDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserTokenDTO;
import kz.bitlab.middle02.keycloakservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String getItems(){
        return "This is login page";
    }

    @PostMapping(value = "/token")
    public UserTokenDTO signIn(@RequestBody UserLoginDTO userLoginDTO){
        return userService.authenticate(userLoginDTO);
    }

}
