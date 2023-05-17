package app.core.servcies;

import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public String getImage(int couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException("coupon not found !"));
        return coupon.getImage();
    }
}
