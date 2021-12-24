package vml.travelagency.service;

import org.springframework.stereotype.Service;
import vml.travelagency.dto.request.RegisterUserRequestDto;
import vml.travelagency.model.User;

import java.util.List;

@Service
public interface UserService {

    User registerUser(RegisterUserRequestDto userDto);

    User create(User user);

    User readById(long id);

    User update(User role);

    User getByEmail(String email);

    List<User> getAll();
}
