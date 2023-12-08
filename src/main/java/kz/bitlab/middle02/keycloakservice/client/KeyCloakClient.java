package kz.bitlab.middle02.keycloakservice.client;


import kz.bitlab.middle02.keycloakservice.dto.UserCreateDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserLoginDTO;
import kz.bitlab.middle02.keycloakservice.dto.UserTokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakClient {

    private final Keycloak keycloak;
    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public UserRepresentation createUser(UserCreateDTO userCreateDTO) {

        UserRepresentation newUser = new UserRepresentation();
        newUser.setEmail(userCreateDTO.getEmail());
        newUser.setEmailVerified(true);
        newUser.setUsername(userCreateDTO.getUsername());
        newUser.setFirstName(userCreateDTO.getFirstName());
        newUser.setLastName(userCreateDTO.getLastName());
        newUser.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(userCreateDTO.getPassword());
        credentials.setTemporary(false);

        newUser.setCredentials(List.of(credentials));
        Response response = keycloak
                .realm(realm)
                .users()
                .create(newUser);

        if (response.getStatus() != HttpStatus.CREATED.value()) {
            log.error("Error on creating user, status : {}", response.getStatus());
            throw new RuntimeException("Failed to create user in keycloak, status = " + response.getStatus());
        }

        List<UserRepresentation> userList = keycloak.realm(realm)
                .users()
                .search(userCreateDTO.getUsername());

        return userList.get(0);
    }

    public UserTokenDTO signIn(UserLoginDTO userLoginDTO) {

        String tokenEndpoint = url + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", userLoginDTO.getUsername());
        formData.add("password", userLoginDTO.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<Map> response = restTemplate
                .postForEntity(tokenEndpoint, new HttpEntity<>(formData, headers), Map.class);

        Map<String, Object> responseBody = response.getBody();

        if (!response.getStatusCode().is2xxSuccessful() || responseBody == null) {
            log.error("Error on signing in with user {}", userLoginDTO.getUsername());
            throw new RuntimeException("Error on signing in with user, status = " + response.getStatusCode());
        }

        UserTokenDTO userToken = new UserTokenDTO();
        userToken.setToken((String) responseBody.get("access_token"));
        userToken.setRefreshToken((String) responseBody.get("refresh_token"));

        return userToken;

    }

}