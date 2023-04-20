package app.core.security;

import app.core.connectionsystem.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentials {

    private String email;
    private String password;
    private ClientType clientType;
}