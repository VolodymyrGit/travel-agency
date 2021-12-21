package vml.travelagency.service;

import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;

import java.util.List;

public interface BookingPeriodService {

    void checkIfPeriodsAreActive();
    List<BookingPeriod> getAllByRoom(Room room);
}
