package app.core.servcies;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService extends ClientService {

    private Company company;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            this.company = companyRepository.findByEmailAndPassword(email, password);
            System.out.println("Welcome : " + email + " !");
            return true;
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException {
        Optional<Coupon> optional = couponRepository.findById(coupon.getId());
        if (optional.isEmpty()) {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            if (coupon.getEndDate().before(date)) {
                throw new CouponSystemException("Coupon passed deadline");
            }
            if (couponRepository.existsByCompanyIdAndTitle(coupon.getCompany().getId(), coupon.getTitle())) {
                throw new CouponSystemException("Check the coupon name and try again !");
            }
            couponRepository.save(coupon);
            System.out.println("Coupon added successfully !");
        }
    }

    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        Coupon tempCoupon = couponRepository.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException("Coupon doesn't exist !"));
        if (tempCoupon.getId() == coupon.getId() && tempCoupon.getCompany().getId() == coupon.getCompany().getId()) {
            couponRepository.save(coupon);
            System.out.println("Coupon updated successfully !");
        } else {
            throw new CouponSystemException("You cannot change company id or coupon id ");
        }
    }

    public void deleteCoupon(Coupon coupon) throws CouponSystemException {
        couponRepository.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException("Coupon doesn't exist !"));
        couponRepository.deleteById(coupon.getId());
        System.out.println("Coupon deleted successfully !");
    }

    public List<Coupon> getCompanyCoupons() throws CouponSystemException {
        try {
            return couponRepository.findAllCouponsByCompanyId(this.company.getId());
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
        try {
            return couponRepository.findAllByCompanyIdAndCategory(company.getId(), category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
        try {
            return couponRepository.findAllByCompanyIdAndPriceLessThan(company.getId(), maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public Company getCompanyDetails() throws CouponSystemException {
        List<Coupon> coupons;
        try {
            coupons = couponRepository.findAllCouponsByCompanyId(this.company.getId());
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
        this.company.setCoupons(coupons);

        return this.company;
    }
}
