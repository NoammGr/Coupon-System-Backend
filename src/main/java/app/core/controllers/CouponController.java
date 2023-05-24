package app.core.controllers;

import app.core.entities.Coupon;
import app.core.servcies.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getImage(@RequestParam int couponId) {
        return "http://localhost:8080/" + couponService.getImage(couponId);
    }
}
