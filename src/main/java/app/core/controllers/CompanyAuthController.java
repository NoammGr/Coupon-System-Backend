package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.Company;
import app.core.servcies.CompanyJwtService;
import app.core.servcies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class CompanyAuthController {
    @Autowired
    CompanyJwtService companyJwtService;
    @Autowired
    CompanyService companyService;

    @PostMapping(path = "/company-login")
    public String login(@RequestBody UserCredentials userCredentials) {
        try {
            companyService.login(userCredentials.getEmail(), userCredentials.getPassword());
            return companyJwtService.login(userCredentials);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping(path = "/company-register")
    public String register(@RequestBody Company company) {
        try {
            return this.companyJwtService.register(company);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
