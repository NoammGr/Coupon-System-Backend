package app.core.auth;

import app.core.connectionsystem.ClientType;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil extends JwtUtilAbstract<UserCredentials, Integer> {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String generateToken(UserCredentials userCredentials) {
        switch (userCredentials.getClientType()) {
            case ADMIN -> {
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", userCredentials.getEmail());
                claims.put("clientType", userCredentials.getClientType());
                return this.createToken(claims, 0);
            }
            case COMPANY -> {
                Company company = companyRepository.findByEmail(userCredentials.getEmail());
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", userCredentials.getEmail());
                claims.put("clientType", userCredentials.getClientType());
                return this.createToken(claims, company.getId());
            }
            case CUSTOMER -> {
                Customer customer = customerRepository.findByEmail(userCredentials.getEmail());
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", userCredentials.getEmail());
                claims.put("clientType", userCredentials.getClientType());
                return this.createToken(claims, customer.getId());
            }
        }
        throw new CouponSystemException("check email password or client type again !");
    }

    @Override
    public UserCredentials extractUser(String token) throws JwtException {
        Claims claims = this.extractAllClaims(token);
        String email = claims.get("email", String.class);
        ClientType clientType = ClientType.valueOf(claims.get("clientType", String.class));
        return UserCredentials.builder().email(email).password(null).clientType(clientType).build();
    }
}
