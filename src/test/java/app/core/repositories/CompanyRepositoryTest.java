package app.core.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CompanyRepositoryTest {
    @Autowired
    CompanyRepository companyRepository;

    @Test
    void findByEmailAndPassword() {
        System.out.println("Test 1 started !");
        String email = "aaabbb@gmail.com";
        String password = "aaaabbbb";
        assertAll(() -> System.out.println(companyRepository.findByEmailAndPassword(email, password)));
        System.out.println("Test 1 completed !");
    }

    @Test
    void existsByEmailAndPassword() {
        System.out.println("Test 2 started !");
        String email = "aaabbb@gmail.com";
        String password = "aaaabbbb";
        assertTrue(companyRepository.existsByEmailAndPassword(email, password));
        System.out.println("Test 2 completed !");
    }

    @Test
    void count(){
        int number = (int) companyRepository.count();
        System.out.println(number);
    }
}