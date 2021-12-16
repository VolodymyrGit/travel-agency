package vml.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Role;
import vml.travelagency.model.Room;
import vml.travelagency.model.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class JwtSubjectUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private List<Room> rooms;
    private List<Hotel> hotels;

    public JwtSubjectUserDto userToDto(User user) {
        JwtSubjectUserDto build = JwtSubjectUserDto.builder().firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .rooms(user.getRooms())
                .hotels(user.getHotels())
                .build();
        return build;
    }
}
