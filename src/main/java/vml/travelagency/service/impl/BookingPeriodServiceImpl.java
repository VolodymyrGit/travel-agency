package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.request.BookingRequestDto;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Room;
import vml.travelagency.model.User;
import vml.travelagency.repository.BookingPeriodRepo;
import vml.travelagency.service.BookingPeriodService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingPeriodServiceImpl implements BookingPeriodService {

    private final BookingPeriodRepo periodRepo;

    @Override
    public BookingPeriod create(BookingPeriod bookingPeriod) {
        if (bookingPeriod != null) {
            return periodRepo.save(bookingPeriod);
        }
        log.error("Method create: BookingPeriod parameter can't be 'null'");
        throw new NullEntityReferenceException("BookingPeriod cannot be 'null'");
    }

    @Override
    public BookingPeriod createFromUserRoomAndRequestDto(User user, Room room, BookingRequestDto requestDto) {
        BookingPeriod bookingPeriod = BookingPeriod.builder()
                .user(user)
                .room(room)
                .bookingDay(requestDto.getBeginDay())
                .endBookingDay(requestDto.getEndDay())
                .isActive(true)
                .build();
        return create(bookingPeriod);
    }

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
