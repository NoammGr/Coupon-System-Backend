package app.core.auth;

import app.core.entities.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdminJwtUtil extends JwtUtilAbstract<Admin, String> {
    @Override
    public String generateToken(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", admin.getEmail());
        return this.createToken(claims, "admin");
    }


    @Override
    public Admin extractUser(String token) throws JwtException {
        Claims claims = this.extractAllClaims(token);
        String email = claims.get("email", String.class);
        return Admin.builder().email(email).password(null).build();
    }
}
