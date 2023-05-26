package app.core.controllers;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.CouponForm;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/company/api")
@CrossOrigin
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping(path = "/add-coupon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addCoupon(CouponForm couponForm) throws CouponSystemException {
        String fileUploadPath = "src/main/resources/static/images";
        String originalFileName = couponForm.getImage().getOriginalFilename();
        String absolutePath = Paths.get(fileUploadPath).toAbsolutePath().normalize().toString();
        File destinationFile = new File(absolutePath, Objects.requireNonNull(originalFileName));
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
                .image("images/" + originalFileName)
                .build();
        try {
            companyService.addCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @PutMapping("/update-coupon")
    public void updateCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        try {
            companyService.updateCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @DeleteMapping("/delete-coupon")
    public void deleteCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        try {
            companyService.deleteCoupon(coupon);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping("/get-all-company-coupons")
    public List<Coupon> getCompanyCoupons(@RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping("/get-all-coupons-category")
    public List<Coupon> getCompanyCoupons(@RequestParam Category category, @RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId, category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping("/get-all-coupons-maxPrice")
    public List<Coupon> getCompanyCoupons(@RequestParam double maxPrice, @RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyCoupons(companyId, maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping("/get-company-details")
    public Company getCompanyDetails(@RequestParam int companyId) throws CouponSystemException {
        try {
            return companyService.getCompanyDetails(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }
}
