package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.Customer;
import app.core.servcies.CustomerJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class CustomerAuthController {
    @Autowired
    CustomerJwtService customerJwtService;

    @PostMapping(path = "/customer-login")
    public String login(@RequestBody UserCredentials userCredentials) {
        try {
            return customerJwtService.login(userCredentials);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping(path = "/customer-register")
    public String register(@RequestBody Customer customer) {
        try {
            return this.customerJwtService.register(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
