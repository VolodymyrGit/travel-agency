package vml.travelagency.service;

import vml.travelagency.dto.response.HotelResponseDto;

import java.util.List;

public interface HotelService {

    List<HotelResponseDto> getAllHotelResponseDtoByCountryName(String countryName);
}
