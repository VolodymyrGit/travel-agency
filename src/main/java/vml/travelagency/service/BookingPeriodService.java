package vml.travelagency.service;

import vml.travelagency.dto.request.BookingRequestDto;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Room;
import vml.travelagency.model.User;

import java.util.List;

public interface BookingPeriodService {

    BookingPeriod create(BookingPeriod bookingPeriod);

    BookingPeriod createFromUserRoomAndRequestDto(User user, Room room, BookingRequestDto requestDto);

    void checkIfPeriodsAreActive();

    List<BookingPeriod> getAllByRoom(Room room);
}
