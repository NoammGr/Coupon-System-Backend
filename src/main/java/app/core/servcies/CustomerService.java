package app.core.servcies;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import app.core.auth.UserCredentials;
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

//    private Customer customer;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean login(UserCredentials userCredentials) throws CouponSystemException {
        return customerRepository.findByEmail(userCredentials.getEmail()) != null;
    }

    public void purchaseCoupon(int id, int customerId) throws CouponSystemException {
        Coupon tempCoupon = couponRepository.findById(id).orElseThrow(() -> new CouponSystemException("Coupon not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Customer not found"));
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if (couponRepository.existsByCustomersIdAndId(customerId, tempCoupon.getId())) {
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

    public List<Coupon> getCustomerCoupon(int customerId) throws CouponSystemException {
        try {
            return couponRepository.findByCustomersId(customerId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public List<Coupon> getCustomerCoupon(int customerId, Category category) throws CouponSystemException {
        try {
            return couponRepository.findByCustomersIdAndCategory(customerId, category);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public List<Coupon> getCustomerCoupon(int customerId, double maxPrice) throws CouponSystemException {
        try {
            return couponRepository.findByCustomersIdAndPriceLessThan(customerId, maxPrice);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("Customer coupons doesn't found !" + e);
        }
    }

    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Customer not found !"));
    }
}
