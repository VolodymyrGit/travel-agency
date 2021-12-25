package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vml.travelagency.model.Room;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDto {

    private String hotelName;
    private Long roomNumber;
    private String roomType;
    private BigDecimal roomPrice;

    public static RoomResponseDto toDto(Room room) {
        RoomResponseDto responseDto = new RoomResponseDto();
        responseDto.setHotelName(room.getHotel().getHotelName());
        responseDto.setRoomNumber(room.getRoomNumber().getNumber());
        responseDto.setRoomType(room.getRoomType().name());
        responseDto.setRoomPrice(room.getRoomPrice());
        return responseDto;
    }
}
