package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {

    private String hotelName;
    private List<RoomResponseDto> rooms;
}
