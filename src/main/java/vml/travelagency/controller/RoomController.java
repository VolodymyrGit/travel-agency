package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.RoomRequestDto;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.service.HotelService;
import vml.travelagency.service.RoomService;

import javax.validation.Valid;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final HotelService hotelService;
    private final RoomService roomService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<RoomResponseDto> createCountry(@Valid @RequestBody RoomRequestDto requestDto) {

        Hotel hotel = hotelService.getByHotelName(requestDto.getHotelName());
        hotelService.checkIfRoomNumberNotExist(hotel, requestDto.getRoomNumber());
        Room room = roomService.createFromHotelAndRoomRequestDto(hotel, requestDto);
        RoomResponseDto responseDto = RoomResponseDto.toDto(room);
        log.info("New Room was created");
        return ResponseEntity.ok(responseDto);
    }
}
