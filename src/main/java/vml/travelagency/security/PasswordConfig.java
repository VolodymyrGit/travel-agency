package vml.travelagency.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}