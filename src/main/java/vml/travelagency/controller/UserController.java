package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.RegisterUserRequestDto;
import vml.travelagency.dto.response.UserResponseDto;
import vml.travelagency.model.User;
import vml.travelagency.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('MANAGER') or isAnonymous()")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody RegisterUserRequestDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserResponseDto> responseDtos = users.stream().map(UserResponseDto::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }
}
