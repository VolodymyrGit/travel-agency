package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.BookingAvailableRequestDto;
import vml.travelagency.dto.request.CountryRequestDto;
import vml.travelagency.dto.response.CountryResponseDto;
import vml.travelagency.dto.response.HotelAvailableRoomResponseDto;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.service.HotelService;
import vml.travelagency.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelsController {

    private final HotelService hotelService;
    private final RoomService roomService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/all/country/find")
    public ResponseEntity<CountryResponseDto> findAllHotelsInCountry(@RequestBody CountryRequestDto requestDto) {
        return ResponseEntity.ok(new CountryResponseDto(hotelService
                .getAllHotelResponseDtoByCountryName(requestDto.getCountryName())));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/rooms/available")
    public ResponseEntity<HotelAvailableRoomResponseDto> findAllAvailableRoomsForPeriod(
            @RequestBody BookingAvailableRequestDto bookingRequestDto) {
        Hotel hotel = hotelService.getByHotelName(bookingRequestDto.getHotelName());
        List<Room> hotelRooms = roomService.getAllRoomsByHotel(hotel);
        List<Room> availableRooms = roomService
                .getAllAvailableRooms(hotelRooms, bookingRequestDto.getBeginDay(), bookingRequestDto.getEndDay());
        List<RoomResponseDto> roomResponseDtos = roomService.roomsToRoomResponseDtos(availableRooms);
        HotelAvailableRoomResponseDto availableRoomsDto = new HotelAvailableRoomResponseDto(roomResponseDtos);
        return ResponseEntity.ok(availableRoomsDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/room/available/book")
    public ResponseEntity<HttpStatus> bookAvailableRoom() {


        return null;
    }
}
