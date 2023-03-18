package app.core.auth;

import app.core.entities.Company;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompanyJwtUtil extends JwtUtilAbstract<Company, Integer> {
    @Override
    public String generateToken(Company company) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", company.getName());
        claims.put("email", company.getEmail());
        return this.createToken(claims, company.getId());
    }

    @Override
    public Company extractUser(String token) throws JwtException {
        Claims claims = this.extractAllClaims(token);
        int id = Integer.parseInt(claims.getSubject());
        String name = claims.get("name", String.class);
        String email = claims.get("email", String.class);
        return Company.builder().id(id).name(name).email(email).password(null).coupons(null).build();
    }
}
