package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vml.travelagency.dto.request.RoomRequestDto;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.model.RoomType;
import vml.travelagency.repository.RoomRepo;
import vml.travelagency.service.RoomService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Override
    public Room create(Room room) {
        if (room == null) {
            log.error("Method create, room parameter - 'null'");
            throw new NullEntityReferenceException("Room parameter can't be 'null'");
        }
        return roomRepo.save(room);
    }

    @Override
    public Room createFromHotelAndRoomRequestDto(Hotel hotel, RoomRequestDto requestDto) {
        Room room = Room.builder()
                .hotel(hotel)
                .roomNumber(requestDto.getRoomNumber())
                .roomType(RoomType.valueOf(requestDto.getRoomType()))
                .roomPrice(requestDto.getRoomPrice())
                .bookingPeriods(new ArrayList<>())
                .build();
        return create(room);
    }

    @Override
    public List<Room> getAllRoomsByHotel(Hotel hotel) {
        return roomRepo.findAllByHotel(hotel);
    }

    @Override
    public List<Room> getAllAvailableRooms(List<Room> rooms,
                                           LocalDate beginDay,
                                           LocalDate endDay) {
        return rooms.stream()
                .filter(room -> checkIfRoomAvailableForBook(room.getBookingPeriods(), beginDay, endDay))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfRoomAvailableForBook(List<BookingPeriod> bookingPeriods,
                                               LocalDate beginDay,
                                               LocalDate endDay) {
        boolean isAvailable = false;
        if (bookingPeriods.size() == 0) return true;
        for (BookingPeriod bookPeriod : bookingPeriods) {
            if (beginDay.isAfter(bookPeriod.getEndBookingDay()) || endDay.isBefore(bookPeriod.getBookingDay())) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    @Override
    public Room getByHotelAndRoomNumber(Hotel hotel, Long roomNumber) {
        return roomRepo.findByHotelAndRoomNumber(hotel, roomNumber)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Can't find Room with this Hotel - %s, RoomNumber - %s", hotel, roomNumber)));
    }
}
