package app.core.controllers;

import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/company/api")
public class CompanyController {
    @Autowired
    LoginManager loginManager = new LoginManager();
    @Autowired
    CompanyService companyService;

    @PostMapping(path = "/login")
    public CompanyService login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        try {
            return (CompanyService) loginManager.login(email, password, ClientType.COMPANY);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Login failed- " + e);
        }
    }

    @PostMapping(path = "/add-coupon")
    public void addCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        try {
            companyService.addCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in adding coupon- " + e);
        }
    }

    @PutMapping("/update-coupon")
    public void updateCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        try {
            companyService.updateCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in updating coupon method- " + e);
        }
    }

    @DeleteMapping("/delete-coupon")
    public void deleteCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        try {
            companyService.deleteCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in deleting coupon method- " + e);
        }
    }

    @GetMapping("/get-all-company-coupons")
    public List<Coupon> getCompanyCoupons() throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-all-coupons-category")
    public List<Coupon> getCompanyCoupons(@RequestParam Category category) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-all-coupons-maxPrice")
    public List<Coupon> getCompanyCoupons(@RequestParam double maxPrice) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-company-details")
    public Company getCompanyDetails() throws CouponSystemException {
        try {
            return companyService.getCompanyDetails();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company details method- " + e);
        }
    }
}
