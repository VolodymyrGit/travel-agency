package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.response.HotelResponseDto;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.repository.HotelRepo;
import vml.travelagency.service.HotelService;
import vml.travelagency.service.RoomService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;
    private final RoomService roomService;


    @Override
    public Hotel getByHotelName(String hotelName) {
        if (hotelName == null) throw new NullEntityReferenceException();
        return hotelRepo.findByHotelName(hotelName)
                .orElseThrow(() -> new EntityNotFoundException("Can't find Hotel by this hotelName" + hotelName));
    }

    @Override
    public List<Hotel> getAllByCountryName(String countryName) {
        return hotelRepo.findAllByCountryName(countryName);
    }

    @Override
    public List<HotelResponseDto> getAllHotelResponseDtoByCountryName(String countryName) {
        List<Hotel> countryHotels = getAllByCountryName(countryName);
        return countryHotels.stream()
                .map(hotel -> {
                    List<Room> hotelRooms = roomService.getAllRoomsByHotel(hotel);
                    List<RoomResponseDto> roomResponseDtos = roomService.roomsToRoomResponseDtos(hotelRooms);
                    return new HotelResponseDto(hotel.getHotelName(), roomResponseDtos);
                })
                .collect(Collectors.toList());
    }
}