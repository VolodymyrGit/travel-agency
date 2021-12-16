//package vml.travelagency.security;
//
//import com.google.gson.Gson;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import vml.travelagency.exceptions.InvalidJwtTokenException;
//import vml.travelagency.repository.UserRepo;
//
//import java.util.Date;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtProvider {
//
//    @Value("${spring.security.jwt.secret}")
//    private String jwtSecret;
//    private final Gson gson;
//    private final UserRepo userRepo;
//
//    public String generateToken(String email, String role, int validDays, int validHours) {
////        User user = userRepo.findByEmail(email).orElseThrow(() -> {
////            log.info(String.format("generateToken method - User: %s, not found", email));
////            return new UsernameNotFoundException(String.format("generateToken method - User: %s, not found", email));
////        });
//        Claims claims = Jwts.claims().setSubject(email);
//        claims.put("role", role);
//
//        Date newTokenDate = new Date();
//        Date expirationDate = new DateTime().plusDays(validDays).plusHours(validHours).toDate();
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(newTokenDate)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (InvalidJwtTokenException e) {
//            log.warn("Jwt token is not valid");
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public SecurityUserRequestDto getLoginPasswordFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//        String subject = claims.getSubject();
//        return gson.fromJson(subject, SecurityUserRequestDto.class);
//    }
//}
