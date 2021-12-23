package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vml.travelagency.model.RoomNumber;

import java.util.Optional;

public interface RoomNumberRepo extends JpaRepository<RoomNumber, Long> {

    Optional<RoomNumber> findByNumber(Long roomNumber);
}
