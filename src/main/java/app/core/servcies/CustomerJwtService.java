package app.core.servcies;

import app.core.auth.CustomerJwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerJwtService {
    @Autowired
    CustomerJwtUtil customerJwtUtil;
    @Autowired
    CustomerRepository customerRepository;

    public String register(Customer customer) throws Exception {
        if (!customerRepository.existsByEmail(customer.getEmail())) {
            this.customerRepository.save(customer);
            return this.customerJwtUtil.generateToken(customer);
        } else {
            throw new Exception("Register failed - Email already in use - " + customer.getEmail());
        }
    }

    public String login(UserCredentials userCredentials) throws Exception {
        Customer customer = customerRepository.findByEmail(userCredentials.getEmail());
        if (customer != null) {
            if (userCredentials.getPassword().equals(customer.getPassword())) {
                return customerJwtUtil.generateToken(customer);
            } else {
                throw new Exception("Wrong email or password ");
            }
        }
        throw new CouponSystemException("User email not found !");
    }

}
