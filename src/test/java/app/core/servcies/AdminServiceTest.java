package app.core.servcies;

import app.core.security.UserCredentials;
import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Company;
import app.core.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {
    @Autowired
    LoginManager loginManager;
    @Autowired
    AdminService adminService;

    Company company1 = Company.builder().id(1).name("Intel").email("Intel@gmail.com").password("aaabbb").build();
    Company company2 = Company.builder().id(2).name("Applied Materials").email("AMAT@gmail.com").password("aaabbb").build();
    Customer customer1 = Customer.builder().id(1).firstName("Noam").lastName("Dov").email("NoamDov@gmail.com").password("12343123").coupons(null).build();
    Customer customer2 = Customer.builder().id(2).firstName("Dov").lastName("Noam").email("DovNoam@gmail.com").password("12343123").coupons(null).build();

    @Test
    void login() {
        System.out.println("Test 1 started !");
        String email = "admin@admin.com";
        String password = "admin";
        assertTrue(adminService.login(email, password));
        System.out.println("Test 1 completed !");
    }

    @Test
    void addCompany() {
        System.out.println("Test 2 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.addCompany(company1));
        assertAll(() -> adminService.addCompany(company2));
        System.out.println("Test 2 completed !");
    }

    @Test
    void updateCompany() {
        System.out.println("Test 3 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.updateCompany(company1));
        System.out.println("Test 3 completed !");
    }

    @Test
    void deleteCompany() {
        System.out.println("Test 4 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.deleteCompany(company1));
        assertAll(() -> adminService.deleteCompany(company2));
        System.out.println("Test 4 completed !");
    }

    @Test
    void getAllCompanies() {
        System.out.println("Test 5 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> System.out.println(adminService.getAllCompanies()));
        System.out.println("Test 5 completed !");
    }

    @Test
    void getOneCompany() {
        System.out.println("Test 6 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> System.out.println(adminService.getOneCompany(1)));
        assertAll(() -> System.out.println(adminService.getOneCompany(2)));
        System.out.println("Test 6 completed !");
    }

    @Test
    void addCustomer() {
        System.out.println("Test 7 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.addCustomer(customer1));
        assertAll(() -> adminService.addCustomer(customer2));
        System.out.println("Test 7 completed !");
    }

    @Test
    void updateCustomer() {
        System.out.println("Test 8 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.updateCustomer(customer1));
        assertAll(() -> adminService.updateCustomer(customer2));
        System.out.println("Test 8 completed !");
    }

    @Test
    void deleteCustomer() {
        System.out.println("Test 9 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> adminService.deleteCustomer(customer1));
        assertAll(() -> adminService.deleteCustomer(customer2));
        System.out.println("Test 9 completed !");
    }

    @Test
    void getAllCustomers() {
        System.out.println("Test 10 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> System.out.println(adminService.getAllCustomers()));
        System.out.println("Test 10 completed !");
    }

    @Test
    void getOneCustomer() {
        System.out.println("Test 11 started !");
        String email = "admin@admin.com";
        String password = "admin";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.ADMIN).build();
        String adminJwt = loginManager.login(userCredentials);
        System.out.println(adminJwt);
        assertAll(() -> System.out.println(adminService.getOneCustomer(1)));
        assertAll(() -> System.out.println(adminService.getOneCustomer(2)));
        System.out.println("Test 11 completed !");
    }
}