package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
