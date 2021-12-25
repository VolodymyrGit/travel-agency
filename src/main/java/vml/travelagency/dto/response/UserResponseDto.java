package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Role;
import vml.travelagency.model.Room;
import vml.travelagency.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private List<BookingResponseDto> bookingPeriods;

    public static UserResponseDto toDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setBookingPeriods(user.getBookingPeriods()
                .stream()
                .map(BookingResponseDto::toDto)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
