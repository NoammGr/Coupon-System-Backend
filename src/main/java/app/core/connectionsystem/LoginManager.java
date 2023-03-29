package app.core.connectionsystem;

import app.core.auth.AdminJwtUtil;
import app.core.auth.CompanyJwtUtil;
import app.core.auth.CustomerJwtUtil;
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
    AdminJwtUtil adminJwtUtil;
    @Autowired
    AdminService adminService;
    @Autowired
    CompanyJwtUtil companyJwtUtil;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerJwtUtil customerJwtUtil;
    @Autowired
    CustomerRepository customerRepository;

    public String login(String email, String password, ClientType client) throws CouponSystemException {
        switch (client) {
            case ADMIN -> {
                Admin admin = Admin.builder().email("admin@admin.com").password("admin").build();
                if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
                    adminService.login(email, password);
                    return adminJwtUtil.generateToken(admin);
                } else {
                    throw new CouponSystemException("Wrong email or password ");
                }
            }
            case COMPANY -> {
                Company company = companyRepository.findByEmail(email);
                if (company != null) {
                    if (password.equals(company.getPassword())) {
                        companyService.login(email, password);
                        return companyJwtUtil.generateToken(company);
                    } else {
                        throw new CouponSystemException("Wrong password ");
                    }
                } else {
                    throw new CouponSystemException("Wrong email ");
                }
            }
            case CUSTOMER -> {
                if (customerService.login(email, password)) {
                    Customer customer = customerRepository.findByEmail(email);
                    if (customer != null) {
                        if (password.equals(customer.getPassword())) {
                            customerService.login(email, password);
                            return customerJwtUtil.generateToken(customer);
                        } else {
                            throw new CouponSystemException("Wrong password ");
                        }
                    } else {
                        throw new CouponSystemException("Wrong email");
                    }
                }
            }
        }
        throw new CouponSystemException("Wrong client type !");
    }
}