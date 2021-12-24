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
        return RoomResponseDto.builder()
                .hotelName(room.getHotel().getHotelName())
                .roomNumber(room.getRoomNumber().getNumber())
                .roomType(room.getRoomType().name())
                .roomPrice(room.getRoomPrice())
                .build();
    }
}
