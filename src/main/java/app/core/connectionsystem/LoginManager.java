package app.core.connectionsystem;

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
    AdminService adminService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    public LoginManager() {

    }

    private static LoginManager instance;

    public static LoginManager getInstance() throws CouponSystemException {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientService login(String name, String password, ClientType client) throws CouponSystemException {

        if (client == (ClientType.Administrator)) {
            if (adminService.login(name, password)) {
                return this.adminService;
            }
        }

        if (client == (ClientType.Company)) {
            if (companyService.login(name, password)) {
                return this.companyService;
            }
        }

        if (client == (ClientType.Customer)) {
            if (customerService.login(name, password)) {
                return this.customerService;
            }
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

}