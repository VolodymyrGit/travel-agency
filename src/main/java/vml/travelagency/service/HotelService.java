package vml.travelagency.service;

import vml.travelagency.dto.response.HotelResponseDto;
import vml.travelagency.model.Hotel;

import java.util.List;

public interface HotelService {

    Hotel getByHotelName(String hotelName);
    List<Hotel> getAllByCountryName(String countryName);
}
