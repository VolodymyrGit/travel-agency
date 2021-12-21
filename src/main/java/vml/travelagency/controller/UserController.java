package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.RegisterUserRequestDto;
import vml.travelagency.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody RegisterUserRequestDto userDto, BindingResult result) {
        userService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }
}
