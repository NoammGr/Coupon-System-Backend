package app.core.connectionsystem;

import app.core.auth.UserCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginManagerTest {
    @Autowired
    private LoginManager loginManager;

    private ClientType clientTypeAdmin = ClientType.ADMIN;
    private ClientType clientTypeCompany = ClientType.COMPANY;
    private ClientType clientTypeCustomer = ClientType.CUSTOMER;

    @Test
    void loginAdmin() {
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        assertAll(() -> loginManager.login(userCredentials));
    }

    @Test
    void loginCompany() {
        String email = "Intel@gmail.com";
        String password = "aaabbb";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.COMPANY).build();
        assertAll(() -> loginManager.login(userCredentials));
    }

    @Test
    void loginCustomer() {
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        assertAll(() -> loginManager.login(userCredentials));
    }
}