package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findAllByHotel(Hotel hotel);
}
