package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.BookingAvailableRequestDto;
import vml.travelagency.dto.request.BookingRequestDto;
import vml.travelagency.dto.request.CountryRequestDto;
import vml.travelagency.dto.request.HotelRequestDto;
import vml.travelagency.dto.response.CountryResponseDto;
import vml.travelagency.dto.response.HotelResponseDto;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.BookingPeriod;
import vml.travelagency.model.Country;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.model.RoomNumber;
import vml.travelagency.service.BookingPeriodService;
import vml.travelagency.service.CountryService;
import vml.travelagency.service.HotelService;
import vml.travelagency.service.RoomNumberService;
import vml.travelagency.service.RoomService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final CountryService countryService;
    private final HotelService hotelService;
    private final RoomService roomService;
    private final BookingPeriodService bookingService;
    private final RoomNumberService roomNumberService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<HotelResponseDto> createHotel(
            @Valid @RequestBody HotelRequestDto requestDto) {

        Country country = countryService.getCountryByName(requestDto.getCountryName());
        Hotel hotel = hotelService.createFromCountryAndHotelRequestDto(country, requestDto.getHotelName());
        HotelResponseDto responseDto = new HotelResponseDto(hotel.getHotelName(), new ArrayList<>());
        return ResponseEntity.ok(responseDto);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/all/country/find")
    public ResponseEntity<CountryResponseDto> findAllHotelsInCountry(
            @Valid @RequestBody CountryRequestDto requestDto) {

        List<Hotel> countryHotels = hotelService.getAllByCountryName(requestDto.getCountryName());

        List<HotelResponseDto> hotelResponseDtos = countryHotels.stream()
                .map(hotel -> {
                    List<Room> hotelRooms = roomService.getAllRoomsByHotel(hotel);
                    List<RoomResponseDto> roomResponseDtos = roomService.roomsToRoomResponseDtos(hotelRooms);
                    return new HotelResponseDto(hotel.getHotelName(), roomResponseDtos);
                })
                .collect(Collectors.toList());

        CountryResponseDto countryResponseDto = new CountryResponseDto(requestDto.getCountryName(), hotelResponseDtos);
        return ResponseEntity.ok(countryResponseDto);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/rooms/available")
    public ResponseEntity<HotelResponseDto> findAllAvailableRoomsForPeriod(
            @Valid @RequestBody BookingAvailableRequestDto requestDto) {

        Hotel hotel = hotelService.getByHotelName(requestDto.getHotelName());
        List<Room> hotelRooms = roomService.getAllRoomsByHotel(hotel);
        List<Room> availableRooms = roomService
                .getAllAvailableRooms(hotelRooms, requestDto.getBeginDay(), requestDto.getEndDay());
        List<RoomResponseDto> roomResponseDtos = roomService.roomsToRoomResponseDtos(availableRooms);
        HotelResponseDto responseDto = new HotelResponseDto(requestDto.getHotelName(), roomResponseDtos);
        return ResponseEntity.ok(responseDto);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('USER')")
    @PostMapping("/room/available/book")
    public ResponseEntity<HttpStatus> bookAvailableRoom(@RequestBody @Valid BookingRequestDto requestDto) {

        Hotel hotel = hotelService.getByHotelName(requestDto.getHotelName());
        RoomNumber roomNumber = roomNumberService.getByNumber(requestDto.getRoomNumber());
        Room room = roomService.getByHotelAndRoomNumber(hotel, roomNumber);
        List<BookingPeriod> bookingPeriods = bookingService.getAllByRoom(room);
        boolean isAvailable = roomService
                .checkIfRoomAvailableForBook(bookingPeriods, requestDto.getBeginDay(), requestDto.getEndDay());
        if (isAvailable) {
            bookingService.createFromRoomAndRequestDto(room, requestDto);
            return ResponseEntity.ok().build();
        }
        log.error("bookAvailableRoom : room wasn't booked");
        return ResponseEntity.badRequest().build();
    }
}
