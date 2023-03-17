package app.core.servcies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.entities.Customer;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public class AdminService extends ClientService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Welcome : " + email + " !");
            return true;
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException("company already exists ! ");
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException("company name can't be the same to other company name ! ");
        }
        companyRepository.save(company);
        System.out.println("Success");
    }

    public void updateCompany(Company company) throws CouponSystemException {
        Company comp = companyRepository.findById(company.getId()).orElseThrow(() -> new CouponSystemException("Company doesn't exist !"));
        if (company.getId() != comp.getId()) {
            throw new CouponSystemException("It's impossible to change 'Company id' !");
        }
        if (!company.getName().equals(comp.getName())) {
            throw new CouponSystemException("It's impossible to change 'Company name' !");
        }
        companyRepository.save(company);
        System.out.println("Company updated successfully !");
    }

    public void deleteCompany(Company company) throws CouponSystemException {
        companyRepository.findById(company.getId()).orElseThrow(() -> new CouponSystemException("Company doesn't exist !"));
        couponRepository.deleteAllCouponsByCompanyId(company.getId());
        System.out.println("All company coupons has been deleted ! ");
        companyRepository.deleteById(company.getId());
        System.out.println("Company deleted successfully !");
    }

    public List<Company> getAllCompanies() throws CouponSystemException {
        List<Company> companies;
        try {
            companies = new ArrayList<>(companyRepository.findAll());
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting all companies method !", e);
        }
        return companies;
    }

    public Company getOneCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException("Company doesn't exist !"));
    }

    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException("Customer already exist !");
        }
        customerRepository.save(customer);
        System.out.println("Customer added successfully !");
    }

    public void updateCustomer(Customer customer) throws CouponSystemException {
        Customer custom = customerRepository.findById(customer.getId()).orElseThrow(() -> new CouponSystemException("Customer doesn't exist !"));
        if (customer.getId() != custom.getId()) {
            throw new CouponSystemException("It's impossible to change 'Customer id' !");
        }
        customerRepository.save(customer);
        System.out.println("Customer updated successfully !");
    }

    public void deleteCustomer(Customer customer) throws CouponSystemException {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new CouponSystemException("Customer doesn't exist !"));
        customerRepository.deleteCouponsById(customer.getId());
        System.out.println("All customer coupons has been deleted ! ");
        System.out.println("Customer deleted successfully !");
    }

    public List<Customer> getAllCustomers() throws CouponSystemException {
        List<Customer> customers;
        try {
            customers = new ArrayList<>(customerRepository.findAll());
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting all customers method !", e);
        }
        return customers;
    }

    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Customer doesn't exist !"));
    }

}
