package vml.travelagency.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vml.travelagency.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class SecurityUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private Date expirationDate;

    public static SecurityUserDetails fromUserToSecurityUserDetails(User user) {
        SecurityUserDetails userDetails = new SecurityUserDetails();
        userDetails.login = user.getEmail();
        userDetails.password = user.getPassword();
        userDetails.grantedAuthorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return userDetails;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
