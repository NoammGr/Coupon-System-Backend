package app.core.servcies;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.entities.Customer;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService extends ClientService {

    private Customer customer;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            this.customer = customerRepository.findByEmailAndPassword(email, password);
            System.out.println("Welcome : " + email + " !");
            return true;
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

    public void purchaseCoupon(int id) throws CouponSystemException {
        Coupon tempCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponSystemException("Coupon not found"));
        Customer customer = customerRepository.findById(this.customer.getId())
                .orElseThrow(() -> new CouponSystemException("Customer not found"));
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if (couponRepository.existsByCustomersIdAndId(this.customer.getId(), tempCoupon.getId())) {
            throw new CouponSystemException("You cannot buy the same coupon twice !");
        }
        if (tempCoupon.getEndDate().before(date)) {
            throw new CouponSystemException("Coupon passed deadline");
        }
        if (tempCoupon.getAmount() == 0) {
            throw new CouponSystemException("Coupon out of stock ! please try again later");
        }
        customer.getCoupons().add(tempCoupon);
        tempCoupon.setAmount(tempCoupon.getAmount() - 1);
    }

    public List<Coupon> getCustomerCoupon() throws CouponSystemException {
        try {
            return couponRepository.findByCustomersId(this.customer.getId());
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public List<Coupon> getCustomerCoupon(Category category) throws CouponSystemException {
        try {
            return couponRepository.findByCustomersIdAndCategory(this.customer.getId(), category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public List<Coupon> getCustomerCoupon(double maxPrice) throws CouponSystemException {
        try {
            return couponRepository.findByCustomersIdAndPriceLessThan(this.customer.getId(), maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public Customer getCustomerDetails() throws CouponSystemException {
        List<Coupon> coupons;
        try {
            coupons = new ArrayList<>(couponRepository.findAllCouponsByCustomersId(this.customer.getId()));
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
        this.customer.setCoupons(coupons);
        return this.customer;
    }
}
