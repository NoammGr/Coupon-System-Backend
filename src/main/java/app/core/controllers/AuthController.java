package app.core.controllers;

import app.core.servcies.JwtService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/jwt-token")
    public String token(Authentication authentication) {
        System.out.println(authentication.getCredentials());
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = jwtService.generateToken(authentication);
        LOG.debug("Token granted '{}'", token);
        return token;
    }
}
