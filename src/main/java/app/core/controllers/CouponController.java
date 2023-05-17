package app.core.controllers;

import app.core.entities.Coupon;
import app.core.servcies.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/general/api")
@CrossOrigin
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping(path = "/get-all-coupons")
    public List<Coupon> getAllCoupons() {
        return this.couponService.getAllCoupons();
    }

    @GetMapping(path = "/get-image-id")
    public String getImage(int couponId) {
        return this.couponService.getImage(couponId);
    }
}
