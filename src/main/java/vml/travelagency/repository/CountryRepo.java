package vml.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml.travelagency.model.Country;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String countryName);
}
