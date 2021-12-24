package vml.travelagency.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil implements Serializable {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;
    @Value("${spring.security.jwt.valid-hours}")
    private int JWT_TOKEN_VALID_HOURS;
    @Value("${spring.security.jwt.valid-days}")
    private int JWT_TOKEN_VALID_DAYS;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String email, String role, Long id) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", id);
        claims.put("role", role);

        Date newTokenDate = new Date();
        Date expirationDate = new DateTime().plusDays(JWT_TOKEN_VALID_DAYS).plusHours(JWT_TOKEN_VALID_HOURS).toDate();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(newTokenDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (RuntimeException e) {
            log.info("Token is not valid");
            return false;
        }
    }
}
