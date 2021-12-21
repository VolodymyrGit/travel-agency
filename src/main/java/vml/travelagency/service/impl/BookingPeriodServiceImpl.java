package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Room;
import vml.travelagency.repository.BookingPeriodRepo;
import vml.travelagency.service.BookingPeriodService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingPeriodServiceImpl implements BookingPeriodService {

    private final BookingPeriodRepo periodRepo;

    @Override
    public void checkIfPeriodsAreActive() {
        List<BookingPeriod> allPeriods = periodRepo.findAll();
        LocalDate now = LocalDate.now();
        allPeriods.forEach(bookPeriod -> {
            if (now.isBefore(bookPeriod.getEndBookingDay())) bookPeriod.setIsActive(false);
        });
    }

    @Override
    public List<BookingPeriod> getAllByRoom(Room room) {
        return periodRepo.findAllByRoom(room);
    }
}
