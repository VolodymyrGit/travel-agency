package vml.travelagency.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WebAuthenticationToken implements Authentication {

    private SecurityUserDetails securityUserDetails;
    private boolean isAuthenticated;

    public WebAuthenticationToken(SecurityUserDetails securityUserDetails) {
        this.securityUserDetails = securityUserDetails;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityUserDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return securityUserDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return securityUserDetails;
    }

    @Override
    public Object getPrincipal() {
        return securityUserDetails.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return securityUserDetails.getUsername();
    }
}
