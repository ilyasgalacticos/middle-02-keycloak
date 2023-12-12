package kz.bitlab.middle02.keycloakservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String email;
    private String username;
    private String firstName;
    private String lastName;

}
