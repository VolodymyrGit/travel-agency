package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.response.HotelResponseDto;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.repository.HotelRepo;
import vml.travelagency.service.HotelService;
import vml.travelagency.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;
    private final RoomService roomService;

    @Override
    public List<HotelResponseDto> getAllHotelResponseDtoByCountryName(String countryName) {
        List<Hotel> countryHotels = hotelRepo.findAllByCountryName(countryName);
        return countryHotels.stream()
                .map(hotel -> {
                    String hotelName = hotel.getHotelName();
                    List<RoomResponseDto> roomDtos = roomService.getAllRoomResponseDtoByHotel(hotel);
                    return new HotelResponseDto(hotelName, roomDtos);
                })
                .collect(Collectors.toList());
    }
}