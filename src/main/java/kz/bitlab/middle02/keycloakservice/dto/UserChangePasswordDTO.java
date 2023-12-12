package kz.bitlab.middle02.keycloakservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangePasswordDTO {
    private String newPassword;

}
