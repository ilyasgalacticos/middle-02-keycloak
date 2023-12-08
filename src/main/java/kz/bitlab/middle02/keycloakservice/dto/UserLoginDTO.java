package kz.bitlab.middle02.keycloakservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    private String username;
    private String password;

}
