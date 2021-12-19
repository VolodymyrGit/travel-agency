package vml.travelagency.dto.response;

import vml.travelagency.model.Hotel;
import vml.travelagency.model.Role;
import vml.travelagency.model.Room;

import java.util.List;

public class UserResponse {

    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private List<Hotel> hotels;
    private List<Room> rooms;
}
