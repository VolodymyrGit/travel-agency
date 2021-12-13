package vml.travelagency.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vml.travelagency.exceptions.InvalidJwtTokenException;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private final Gson gson;

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String generateToken(SecurityUserRequestDto securityUserRequestDto) {

        Date date = new DateTime().plusHours(1).toDate();
        String subject = gson.toJson(securityUserRequestDto);

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (InvalidJwtTokenException e) {
            logger.warn("Jwt token is not valid");
            e.printStackTrace();
        }
        return false;
    }

    public SecurityUserRequestDto getLoginPasswordFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String subject = claims.getSubject();
        return gson.fromJson(subject, SecurityUserRequestDto.class);
    }


}
