package app.core.controllers;

import app.core.connectionsystem.ClientType;
import app.core.connectionsystem.LoginManager;
import app.core.exceptions.CouponSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/system")
@CrossOrigin
public class LoginController {
    @Autowired
    LoginManager loginManager;

    @PostMapping(path = "/admin/login")
    public String adminLogin(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        try {
            return loginManager.login(email, password, ClientType.ADMIN);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping(path = "/company/login")
    public String companyLogin(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        try {
            return (String) loginManager.login(email, password, ClientType.COMPANY);
        } catch (CouponSystemException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "/customer/login")
    public String customerLogin(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        try {
            return (String) loginManager.login(email, password, ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}