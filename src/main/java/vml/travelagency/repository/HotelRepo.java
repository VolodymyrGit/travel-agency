package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Hotel;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByHotelName(String hotelName);

    List<Hotel> findAllByCountryName(String countryName);
}
