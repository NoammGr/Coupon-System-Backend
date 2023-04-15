package app.core.entities;

import app.core.connectionsystem.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    @Builder.Default
    private int id = 0;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ClientType clientType = ClientType.ADMIN;
}
