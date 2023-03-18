package app.core.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public abstract class JwtUtilAbstract<T, ID> {
    String alg = SignatureAlgorithm.HS256.getJcaName();

    @Value("${jwt.util.secret.key}")
    private String secret;

    private Key key;
    @Value("${jwt.util.chrono.unit}")
    private ChronoUnit chronoUnit;
    @Value("${jwt.util.chrono.unit.number}")
    private int unitsNumber;

    @PostConstruct
    public void init() {
        this.key = new SecretKeySpec(Base64.getDecoder().decode(secret), alg);
    }

    public abstract String generateToken(T anyUser);

    protected String createToken(Map<String, Object> claims, ID subject) {

        JwtBuilder jwtBuilder = Jwts.builder();

        Instant now = Instant.now();
        Instant exp = now.plus(this.unitsNumber, this.chronoUnit);

        return jwtBuilder

                .setClaims(claims)

                .setSubject(subject.toString())

                .setIssuedAt(Date.from(now))

                .setExpiration(Date.from(exp))

                .signWith(key)

                .compact();
    }

    public abstract T extractUser(String token) throws JwtException;

    protected Claims extractAllClaims(String token) throws JwtException {

        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        return claimsJws.getBody();
    }

}
