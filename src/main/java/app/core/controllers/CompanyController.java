package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.CouponForm;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/company/api")
@CrossOrigin
public class CompanyController {
    @Autowired
    LoginManager loginManager;
    @Autowired
    CompanyService companyService;

    public String login(@RequestBody UserCredentials userCredentials) throws CouponSystemException {
        try {
            return (String) loginManager.login(userCredentials);
        } catch (CouponSystemException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "/add-coupon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addCoupon(CouponForm couponForm) throws CouponSystemException {
        String fileUploadPath = "src/main/resources/static/images";
        String absolutePath = Paths.get(fileUploadPath).toAbsolutePath().normalize().toString();
        File destinationFile = new File(absolutePath, couponForm.getTitle() + ".jpg");
        try {
            couponForm.getImage().transferTo(destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(couponForm);
        Coupon coupon = Coupon.builder()
                .id(couponForm.getId())
                .company(couponForm.getCompany())
                .category(couponForm.getCategory())
                .title(couponForm.getTitle())
                .description(couponForm.getDescription())
                .startDate(couponForm.getStartDate())
                .endDate(couponForm.getEndDate())
                .amount(couponForm.getAmount())
                .price(couponForm.getPrice())
                .image("src/main/resources/static/images/" + couponForm.getTitle())
                .build();
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
    public List<Coupon> getCompanyCoupons(@RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-all-coupons-category")
    public List<Coupon> getCompanyCoupons(@RequestParam Category category, @RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId, category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-all-coupons-maxPrice")
    public List<Coupon> getCompanyCoupons(@RequestParam double maxPrice, @RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId, maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company coupons method- " + e);
        }
    }

    @GetMapping("/get-company-details")
    public Company getCompanyDetails(@RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyDetails(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Error in getting all company details method- " + e);
        }
    }
}
