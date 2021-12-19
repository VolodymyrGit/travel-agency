package vml.travelagency.service;

import org.springframework.stereotype.Service;
import vml.travelagency.dto.request.RegisterUserRequestDto;
import vml.travelagency.model.User;

@Service
public interface UserService {

    User registerUser(RegisterUserRequestDto userDto);

    User create(User user);
}
