package vml.travelagency.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    private final String BEARER = "Bearer ";

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtFilter is running");

        final String token = getTokenFromRequest(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String userLogin = jwtUtil.getUsernameFromToken(token);
            SecurityUserDetails securityUserDetails = userDetailsService.loadUserByUsername(userLogin);
            securityUserDetails.setExpirationDate(jwtUtil.getExpirationDateFromToken(token));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userLogin,
                    null,
                    securityUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authString = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authString)) {
            if (authString.startsWith(BEARER)) {
                return authString.substring(7);
            }
            log.info("authString didn't start with \"Bearer \"");
//            filterChain.doFilter(request, response);
            return null;
        }
        log.info("StringUtils.hasText(authString) - failed");
//        filterChain.doFilter(request, response);
        return null;
    }
}
