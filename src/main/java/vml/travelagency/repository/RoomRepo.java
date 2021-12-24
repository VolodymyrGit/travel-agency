package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.model.RoomNumber;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findAllByHotel(Hotel hotel);

    Optional<Room> findByHotelAndRoomNumber(Hotel hotel, RoomNumber roomNumber);
}
