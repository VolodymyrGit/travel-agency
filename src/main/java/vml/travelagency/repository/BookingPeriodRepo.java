package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Room;

import java.util.List;

@Repository
public interface BookingPeriodRepo extends JpaRepository<BookingPeriod, Long> {

    List<BookingPeriod> findAllByRoom(Room room);
}
