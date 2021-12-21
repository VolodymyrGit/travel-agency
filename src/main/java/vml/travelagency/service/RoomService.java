package vml.travelagency.service;

import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<Room> getAllRoomsByHotel(Hotel hotel);

    List<RoomResponseDto> roomsToRoomResponseDtos(List<Room> rooms);

    List<Room> getAllAvailableRooms(List<Room> rooms, LocalDate beginDay , LocalDate endDay);

    boolean checkIfRoomAvailableForBook(Room room, LocalDate beginDay, LocalDate endDay);
}
