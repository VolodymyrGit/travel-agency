package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vml.travelagency.model.Hotel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class HotelResponseDto {

    private String hotelName;
//    private List<RoomResponseDto> rooms;

    public static HotelResponseDto toDto(Hotel hotel) {
        return new HotelResponseDto(hotel.getHotelName());
    }
}
