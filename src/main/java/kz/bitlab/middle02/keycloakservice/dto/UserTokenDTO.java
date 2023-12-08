package kz.bitlab.middle02.keycloakservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDTO {

    private String token;
    private String refreshToken;
}
