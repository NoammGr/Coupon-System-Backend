package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.servcies.AdminJwtService;
import app.core.servcies.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class AdminAuthController {

    @Autowired
    AdminJwtService adminJwtService;
    @Autowired
    AdminService adminService;

    @PostMapping(path = "/admin-login")
    public String login(@RequestBody UserCredentials userCredentials) {
        try {
            adminService.login(userCredentials.getEmail(), userCredentials.getPassword());
            return adminJwtService.login(userCredentials);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
