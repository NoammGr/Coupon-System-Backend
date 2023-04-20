package app.core.controllers;

import app.core.security.UserCredentials;
import app.core.connectionsystem.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping()
@CrossOrigin
public class LoginController {
    @Autowired
    LoginManager loginManager;

    @PostMapping(path = "/login")
    public String login(@RequestBody UserCredentials userCredentials) {
        try {
            return loginManager.login(userCredentials);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}