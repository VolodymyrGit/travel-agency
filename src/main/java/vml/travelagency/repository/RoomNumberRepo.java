package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vml.travelagency.model.RoomNumber;

public interface RoomNumberRepo extends JpaRepository<RoomNumber, Long> {
}
