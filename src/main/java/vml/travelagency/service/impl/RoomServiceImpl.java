package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.repository.RoomRepo;
import vml.travelagency.service.BookingPeriodService;
import vml.travelagency.service.RoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final BookingPeriodService periodService;

    @Override
    public List<Room> getAllRoomsByHotel(Hotel hotel) {
        return roomRepo.findAllByHotel(hotel);
    }

    @Override
    public List<RoomResponseDto> roomsToRoomResponseDtos(List<Room> rooms) {
        return rooms.stream().map(RoomResponseDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Room> getAllAvailableRooms(List<Room> rooms,
                                           LocalDate beginDay,
                                           LocalDate endDay) {
        return rooms.stream()
                .filter(room -> checkIfRoomAvailableForBook(room, beginDay, endDay))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfRoomAvailableForBook(Room room, LocalDate beginDay, LocalDate endDay) {
        boolean isAvailable = false;
        List<BookingPeriod> bookingPeriods = periodService.getAllByRoom(room);
        if (bookingPeriods.size() == 0) return true;
        for (BookingPeriod bookPeriod : bookingPeriods) {
            if (beginDay.isAfter(bookPeriod.getEndBookingDay()) || endDay.isBefore(bookPeriod.getBookingDay())) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    //                    if (beginDay.isAfter(bookPeriod.getBookingDay()) && beginDay.isBefore(bookPeriod.getBookingDay()) ||
//                            endDay.isAfter(bookPeriod.getBookingDay()) && endDay.isBefore(bookPeriod.getEndBookingDay())) {
//
//                    }
}
