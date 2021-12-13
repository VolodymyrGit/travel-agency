package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vml.travelagency.repository.UserRepo;
import vml.travelagency.service.UserService;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
}
