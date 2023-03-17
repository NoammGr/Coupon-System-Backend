package app.core.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findByEmailAndPassword() {
        System.out.println("Test 1 started !");
        String email = "EldarJB@gmail.com";
        String password = "12314321";
        assertAll(() -> System.out.println(customerRepository.findByEmailAndPassword(email, password)));
        System.out.println("Test 1 completed !");
    }

    @Test
    void findByEmail() {
        System.out.println("Test 2 started !");
        String email = "EldarJB@gmail.com";
        assertAll(() -> System.out.println(customerRepository.findByEmail(email)));
        System.out.println("Test 2 completed !");
    }

    @Test
    void existsByEmailAndPassword() {
        System.out.println("Test 3 started !");
        String email = "EldarJB@gmail.com";
        String password = "12314321";
        assertTrue(customerRepository.existsByEmailAndPassword(email, password));
        System.out.println("Test 3 completed !");
    }
}