package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.response.HotelResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.repository.HotelRepo;
import vml.travelagency.service.HotelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

    @Override
    public List<HotelResponseDto> getAllHotelsByCountryName(String countryName) {
        List<Hotel> countryHotels = hotelRepo.findAllByCountryName(countryName);
        return countryHotels.stream()
                .map(HotelResponseDto::toDto)
                .collect(Collectors.toList());
    }
}