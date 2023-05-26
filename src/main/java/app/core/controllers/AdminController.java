package app.core.controllers;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.servcies.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/api")
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping(path = "/add-company")
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        try {
            adminService.addCompany(company);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @PutMapping(path = "/update-company")
    public void updateCompany(@RequestBody Company company) throws CouponSystemException {
        try {
            adminService.updateCompany(company);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete-company")
    public void deleteCompany(@RequestParam int companyId) throws CouponSystemException {
        try {
            adminService.deleteCompany(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping(path = "/get-all-companies")
    public List<Company> getAllCompanies() throws CouponSystemException {
        try {
            return adminService.getAllCompanies();
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping(path = "/get-one-company")
    public Company getOneCompany(@RequestParam int companyId) throws CouponSystemException {
        try {
            return adminService.getOneCompany(companyId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @PostMapping(path = "/add-customer")
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        try {
            adminService.addCustomer(customer);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @PutMapping(path = "/update-customer")
    public void updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        try {
            adminService.updateCustomer(customer);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete-customer")
    public void deleteCustomer(@RequestParam int customerId) throws CouponSystemException {
        try {
            adminService.deleteCustomer(customerId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping(path = "/get-all-customers")
    public List<Customer> getAllCustomers() throws CouponSystemException {
        try {
            return adminService.getAllCustomers();
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }

    @GetMapping(path = "/get-one-customer")
    public Customer getOneCustomer(@RequestParam int customerId) throws CouponSystemException {
        try {
            return adminService.getOneCustomer(customerId);
        } catch (CouponSystemException e) {
            throw new CouponSystemException(e.getMessage());
        }
    }
}
