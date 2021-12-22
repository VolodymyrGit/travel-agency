package vml.travelagency.service;

import vml.travelagency.model.Country;
import vml.travelagency.model.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel createFromCountryAndHotelRequestDto(Country country, String hotelName);

    Hotel getByHotelName(String hotelName);

    List<Hotel> getAllByCountryName(String countryName);
}
