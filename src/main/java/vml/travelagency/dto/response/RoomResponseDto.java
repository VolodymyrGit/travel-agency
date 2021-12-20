package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vml.travelagency.model.Room;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {

    private String roomType;
    private BigDecimal roomPrice;

    public static RoomResponseDto toDto(Room room) {
        return new RoomResponseDto(room.getRoomType().name(), room.getRoomPrice());
    }
}
