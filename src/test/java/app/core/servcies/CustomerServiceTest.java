package app.core.servcies;

import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    LoginManager loginManager;
    @Autowired
    CustomerService customerService;

    Category category = Category.Restaurant;

    @Test
    void login() {
        System.out.println("Test 1 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertTrue(customerService.login(userCredentials));
        System.out.println("Test 1 completed !");
    }

    @Test
    void purchaseCoupon() {
        System.out.println("Test 2 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertAll(() -> customerService.purchaseCoupon(1, 1));
        assertAll(() -> customerService.purchaseCoupon(2, 1));
        assertAll(() -> customerService.purchaseCoupon(3, 1));
        assertAll(() -> customerService.purchaseCoupon(4, 1));
        assertAll(() -> customerService.purchaseCoupon(5, 1));
        System.out.println("Test 2 completed !");
    }

    @Test
    void getCustomerCoupon() {
        System.out.println("Test 3 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertAll(() -> System.out.println(customerService.getCustomerCoupon(1)));
        System.out.println("Test 3 completed !");
    }

    @Test
    void testGetCustomerCoupon() {
        System.out.println("Test 4 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertAll(() -> System.out.println(customerService.getCustomerCoupon(1, category)));
        System.out.println("Test 4 completed !");
    }

    @Test
    void testGetCustomerCoupon1() {
        System.out.println("Test 5 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertAll(() -> System.out.println(customerService.getCustomerCoupon(1,1001)));
        System.out.println("Test 5 completed !");
    }

    @Test
    void getCustomerDetails() {
        System.out.println("Test 6 started !");
        String email = "NoamDov@gmail.com";
        String password = "12343123";
        UserCredentials userCredentials = UserCredentials.builder().email(email).password(password).clientType(ClientType.CUSTOMER).build();
        String customerJwt = loginManager.login(userCredentials);
        System.out.println(customerJwt);
        assertAll(() -> System.out.println(customerService.getCustomerDetails(1)));
        System.out.println("Test 6 completed !");
    }
}