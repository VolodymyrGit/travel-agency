package vml.travelagency.service;

import vml.travelagency.dto.request.RoomRequestDto;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room create(Room room);

    Room createFromHotelAndRoomRequestDto(Hotel hotel, RoomRequestDto requestDto);

    List<Room> getAllRoomsByHotel(Hotel hotel);

    List<Room> getAllAvailableRooms(List<Room> rooms, LocalDate beginDay , LocalDate endDay);

    boolean checkIfRoomAvailableForBook(List<BookingPeriod> bookingPeriods, LocalDate beginDay, LocalDate endDay);

    Room getByHotelAndRoomNumber(Hotel hotel, Long roomNumber);
}
