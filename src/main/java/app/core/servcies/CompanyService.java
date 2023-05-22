package app.core.servcies;

import app.core.auth.UserCredentials;
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

//    private Company company;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public boolean login(UserCredentials userCredentials) throws CouponSystemException {
        return companyRepository.findByEmail(userCredentials.getEmail()) != null;
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
            Company company = Company.builder().id(coupon.getCompany().getId()).build();
            coupon.setCompany(company);
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

    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException {
        try {
            return couponRepository.findAllCouponsByCompanyId(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException {
        try {
            return couponRepository.findAllByCompanyIdAndCategory(companyId, category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException {
        try {
            return couponRepository.findAllByCompanyIdAndPriceLessThan(companyId, maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Company coupons not found !" + e);
        }
    }

    public Company getCompanyDetails(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException("Company not found !"));
    }
}
