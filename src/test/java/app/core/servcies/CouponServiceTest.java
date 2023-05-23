package app.core.servcies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest

public class CouponServiceTest {
    @Autowired
    CouponService couponService;

    @Test
    void testGetImage() {
        System.out.println(couponService.getImage(14));
        assertAll(() -> couponService.getImage(14));
    }
}
