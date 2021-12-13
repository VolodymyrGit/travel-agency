package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
}
