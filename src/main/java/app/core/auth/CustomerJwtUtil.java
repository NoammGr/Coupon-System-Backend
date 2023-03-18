package app.core.auth;

import app.core.entities.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerJwtUtil extends JwtUtilAbstract<Customer, Integer> {
    @Override
    public String generateToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", customer.getFirstName());
        claims.put("lastName", customer.getLastName());
        claims.put("email", customer.getEmail());
        return this.createToken(claims, customer.getId());
    }

    @Override
    public Customer extractUser(String token) throws JwtException {
        Claims claims = this.extractAllClaims(token);
        int id = Integer.parseInt(claims.getSubject());
        String firstName = claims.get("firstName", String.class);
        String lastName = claims.get("lastName", String.class);
        String email = claims.get("email", String.class);
        return Customer.builder().id(id).firstName(firstName).lastName(lastName).email(email).password(null).coupons(null).build();
    }
}
