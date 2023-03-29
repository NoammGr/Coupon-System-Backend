package app.core.connectionsystem;

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
        assertAll(() -> loginManager.login("admin@admin.com", "admin", clientTypeAdmin));
    }

    @Test
    void loginCompany() {
        assertAll(() -> loginManager.login("Intel@gmail.com", "aaabbb", clientTypeCompany));
        System.out.println(loginManager.login("Intel@gmail.com", "aaabbb", clientTypeCompany));
    }

    @Test
    void loginCustomer() {
        assertAll(() -> loginManager.login("NoamDov@gmail.com", "12343123", clientTypeCustomer));
    }
}