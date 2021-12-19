package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.LoginRequestDto;
import vml.travelagency.dto.response.TokenResponse;
import vml.travelagency.security.JwtUtil;
import vml.travelagency.security.WebAuthenticationManager;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final JwtUtil jwtUtil;
    private final WebAuthenticationManager authenticationManager;

    @PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid LoginRequestDto loginDto
//            @RequestParam(value = "email", required = true)
//            String email,
//            @RequestParam(value = "password", required = true)
//            String password
    ) {
        log.info("Login controller is running");

//        String username = loginDto.getEmail();
//        username = username != null ? username : "";
//        username = username.trim();
//        String password = loginDto.getPassword();
//        password = password != null ? password : "";

        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String role = authentication.getAuthorities().stream().findAny().get().getAuthority();
            String token = jwtUtil.generateToken(loginDto.getEmail(), role);
            return ResponseEntity.ok(new TokenResponse(token));
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
