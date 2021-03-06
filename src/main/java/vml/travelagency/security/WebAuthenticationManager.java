package vml.travelagency.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WebAuthenticationManager implements AuthenticationManager {

    private List<AuthenticationProvider> authenticationProviders;

    @Autowired
    public void setAuthenticationProviders(List<AuthenticationProvider> authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        log.info("WebAuthenticationManager is running");
        Authentication webAuthentication;
        for (AuthenticationProvider authenticationProvider : authenticationProviders) {
            webAuthentication = authenticationProvider.authenticate(authentication);
            if (webAuthentication != null) {
                webAuthentication.setAuthenticated(true);
                return webAuthentication;
            }
        }
        throw new BadCredentialsException("Bad user Credentials!");
    }
}
