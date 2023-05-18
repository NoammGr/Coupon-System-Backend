package app.core.repositories;

import app.core.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class CouponRepositoryTest {
    @Autowired
    CouponRepository couponRepository;

    Category category = Category.RESTAURANT;


    @Test
    void findByTitle() {
        System.out.println("Test 1 started !");
        String title = "Discount on cloth ";
        assertAll(() -> System.out.println(couponRepository.findByTitle(title)));
        System.out.println("Test 1 completed !");
    }

    @Test
    void findByCustomersId() {
        System.out.println("Test 2 started !");
        assertAll(() -> System.out.println(couponRepository.findByCustomersId(1)));
        System.out.println("Test 2 completed !");
    }

    @Test
    void findAllCouponsByCustomersId() {
        System.out.println("Test 3 started !");
        assertAll(() -> System.out.println(couponRepository.findAllCouponsByCompanyId(1)));
        System.out.println("Test 3 completed !");
    }

    @Test
    void findByCustomersIdAndCategory() {
        System.out.println("Test 4 started !");
        assertAll(() -> System.out.println(couponRepository.findByCustomersIdAndCategory(1, category)));
        System.out.println("Test 4 completed !");
    }

    @Test
    void findByCustomersIdAndPriceLessThan() {
        System.out.println("Test 5 started !");
        assertAll(() -> System.out.println(couponRepository.findByCustomersIdAndPriceLessThan(1, 1001)));
        System.out.println("Test 5 completed !");
    }

    @Test
    void findAllCouponsByCompanyId() {
        System.out.println("Test 6 started !");
        assertAll(() -> System.out.println(couponRepository.findAllCouponsByCompanyId(1)));
        System.out.println("Test 6 completed !");
    }

    @Test
    void findAllByCompanyIdAndCategory() {
        System.out.println("Test 7 started !");
        assertAll(() -> System.out.println(couponRepository.findAllByCompanyIdAndCategory(1, category)));
        System.out.println("Test 7 completed !");
    }

    @Test
    void findAllByCompanyIdAndPriceLessThan() {
        System.out.println("Test 8 started !");
        assertAll(() -> System.out.println(couponRepository.findAllByCompanyIdAndPriceLessThan(1, 1001)));
        System.out.println("Test 8 completed !");
    }

    @Test
    @Transactional
    void deleteByEndDateBefore() {
        System.out.println("Test 9 started !");
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        assertAll(() -> couponRepository.deleteByEndDateBefore(date));
        System.out.println("Test 9 completed !");
    }

    @Test
    void deleteById() {
        System.out.println("Test 10 started !");
        assertAll(() -> couponRepository.deleteById(2));
        System.out.println("Test 10 completed !");
    }

}