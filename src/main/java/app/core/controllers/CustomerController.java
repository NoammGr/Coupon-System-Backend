package app.core.controllers;

import app.core.security.UserCredentials;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping(path = "/customer/api")
@CrossOrigin
public class CustomerController {
    @Autowired
    LoginManager loginManager;
    @Autowired
    CustomerService customerService;

    public String login(@RequestBody UserCredentials userCredentials) throws CouponSystemException {
        try {
            return (String) loginManager.login(userCredentials);
        } catch (CouponSystemException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "/coupon-purchase")
    public void purchaseCoupon(@RequestParam int id) throws CouponSystemException {
        try {
            customerService.purchaseCoupon(id);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in purchase coupon method- " + e);
        }
    }

    @GetMapping(path = "/get-customer-coupons")
    public List<Coupon> getCustomerCoupon() throws CouponSystemException {
        try {
            return customerService.getCustomerCoupon();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all customer coupons method- " + e);
        }
    }

    @GetMapping("/get-coupons-category")
    public List<Coupon> getCustomerCoupon(@RequestParam Category category) throws CouponSystemException {
        try {
            return customerService.getCustomerCoupon(category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all customer coupons method- " + e);
        }
    }

    @GetMapping("/get-coupons-maxPrice")
    public List<Coupon> getCustomerCoupon(@RequestParam double maxPrice) throws CouponSystemException {
        try {
            return customerService.getCustomerCoupon(maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all customer coupons method- " + e);
        }
    }

    @GetMapping("/get-customer-details")
    public Customer getCustomerDetails() throws CouponSystemException {
        try {
            return customerService.getCustomerDetails();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all customer details method- " + e);
        }
    }
}
