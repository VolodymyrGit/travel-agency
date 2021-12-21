package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vml.travelagency.dto.request.RegisterUserRequestDto;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.User;
import vml.travelagency.repository.RoleRepo;
import vml.travelagency.repository.UserRepo;
import vml.travelagency.service.UserService;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserRequestDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(roleRepo.getById(2L))
                .hotels(new ArrayList<>())
                .rooms(new ArrayList<>())
                .build();
        create(user);
        return user;
    }

    @Override
    public User create(User user) {
        if (user != null) {
            return userRepo.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }
}
