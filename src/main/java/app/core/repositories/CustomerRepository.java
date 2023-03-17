package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmailAndPassword(String email, String password);

    Customer findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByCouponsId(int couponId);

    void deleteCouponsById(int id);
}
