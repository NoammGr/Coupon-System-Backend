package app.core.connectionsystem;

import app.core.auth.*;
import app.core.entities.Admin;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import app.core.exceptions.CouponSystemException;
import app.core.servcies.AdminService;
import app.core.servcies.ClientService;
import app.core.servcies.CompanyService;
import app.core.servcies.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AdminService adminService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    public String login(UserCredentials userCredentials) throws CouponSystemException {
        switch (userCredentials.getClientType()) {
            case ADMIN -> {
                Admin admin = Admin.builder().email("admin@admin.com").password("admin").build();
                if (userCredentials.getEmail().equals(admin.getEmail()) && userCredentials.getPassword().equals(admin.getPassword())) {
                    adminService.login(userCredentials);
                    return jwtUtil.generateToken(userCredentials);
                } else {
                    throw new CouponSystemException("Wrong email or password ");
                }
            }
            case COMPANY -> {
                Company company = companyRepository.findByEmail(userCredentials.getEmail());
                if (company != null) {
                    if (userCredentials.getPassword().equals(company.getPassword())) {
                        companyService.login(userCredentials);
                        return jwtUtil.generateToken(userCredentials);
                    } else {
                        throw new CouponSystemException("Wrong password ");
                    }
                } else {
                    throw new CouponSystemException("Wrong email ");
                }
            }
            case CUSTOMER -> {
                Customer customer = customerRepository.findByEmail(userCredentials.getEmail());
                if (customer != null) {
                    if (userCredentials.getPassword().equals(customer.getPassword())) {
                        customerService.login(userCredentials);
                        return jwtUtil.generateToken(userCredentials);
                    } else {
                        throw new CouponSystemException("Wrong password ");
                    }
                } else {
                    throw new CouponSystemException("Wrong email");
                }
            }
        }
        throw new CouponSystemException("Wrong client type !");
    }
}