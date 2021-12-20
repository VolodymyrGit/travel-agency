package vml.travelagency.service;

import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;

import java.util.List;

public interface RoomService {

    List<RoomResponseDto> getAllRoomResponseDtoByHotel(Hotel hotel);
}
