package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.LoginDto;
import vml.travelagency.security.JwtProvider;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtProvider jwtUtil;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) {


        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

//    @GetMapping("/login")
//    public ResponseEntity<String> login() {
//        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok("token must be added");
//    }
}
