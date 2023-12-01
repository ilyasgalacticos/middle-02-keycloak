package kz.bitlab.middle02.keycloakservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping
    public String getItems(){
        return "This is login page";
    }

}
