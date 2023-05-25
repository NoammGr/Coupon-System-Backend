package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/api")
@CrossOrigin
public class AdminController {
    @Autowired
    LoginManager loginManager;
    @Autowired
    AdminService adminService;

    public String login(@RequestBody UserCredentials userCredentials) throws CouponSystemException {
        try {
            String token = loginManager.login(userCredentials);
            System.out.println(token);
            return token;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping(path = "/add-company")
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        try {
            adminService.addCompany(company);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in adding company- " + e);
        }
    }

    @PutMapping(path = "/update-company")
    public void updateCompany(@RequestBody Company company) throws CouponSystemException {
        try {
            adminService.updateCompany(company);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in updating company- " + e);
        }
    }

    @DeleteMapping(path = "/delete-company")
    public void deleteCompany(@RequestParam int companyId) throws CouponSystemException {
        try {
            adminService.deleteCompany(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in deleting company- " + e);
        }
    }

    @GetMapping(path = "/get-all-companies")
    public List<Company> getAllCompanies() throws CouponSystemException {
        try {
            return adminService.getAllCompanies();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in getting all company method- " + e);
        }
    }

    @GetMapping(path = "/get-one-company")
    public Company getOneCompany(@RequestParam int companyId) throws CouponSystemException {
        try {
            return adminService.getOneCompany(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in getting one company method- " + e);
        }
    }

    @PostMapping(path = "/add-customer")
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        try {
            adminService.addCustomer(customer);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in adding customer- " + e);
        }
    }

    @PutMapping(path = "/update-customer")
    public void updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        try {
            adminService.updateCustomer(customer);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in updating customer- " + e);
        }
    }

    @DeleteMapping(path = "/delete-customer")
    public void deleteCustomer(@RequestParam int customerId) throws CouponSystemException {
        try {
            adminService.deleteCustomer(customerId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in deleting customer- " + e);
        }
    }

    @GetMapping(path = "/get-all-customers")
    public List<Customer> getAllCustomers() throws CouponSystemException {
        try {
            return adminService.getAllCustomers();
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in adding company- " + e);
        }
    }

    @GetMapping(path = "/get-one-customer")
    public Customer getOneCustomer(@RequestParam int customerId) throws CouponSystemException {
        try {
            return adminService.getOneCustomer(customerId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException("error in adding company- " + e);
        }
    }
}
