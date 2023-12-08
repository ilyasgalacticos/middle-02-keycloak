package kz.bitlab.middle02.keycloakservice.service;


import kz.bitlab.middle02.keycloakservice.client.KeyCloakClient;
import kz.bitlab.middle02.keycloakservice.dto.UserCreateDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserLoginDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserTokenDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final KeyCloakClient keyCloakClient;

    public UserDTO createUser(UserCreateDTO userCreateDTO){

        UserRepresentation userRepresentation = keyCloakClient.createUser(userCreateDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userRepresentation.getUsername());
        userDTO.setEmail(userRepresentation.getEmail());
        userDTO.setFirstName(userRepresentation.getFirstName());
        userDTO.setLastName(userRepresentation.getLastName());

        return userDTO;

    }

    public UserTokenDTO authenticate(UserLoginDTO userLoginDTO){
        return keyCloakClient.signIn(userLoginDTO);
    }

}