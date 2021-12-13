package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.security.JwtUtil;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;

    @PostMapping
    public

//    @GetMapping("/login")
//    public ResponseEntity<String> login() {
//        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok("token must be added");
//    }
}
