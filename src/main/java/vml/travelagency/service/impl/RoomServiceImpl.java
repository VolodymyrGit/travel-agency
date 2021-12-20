package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.response.RoomResponseDto;
import vml.travelagency.model.Hotel;
import vml.travelagency.model.Room;
import vml.travelagency.repository.RoomRepo;
import vml.travelagency.service.RoomService;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Override
    public List<RoomResponseDto> getAllRoomResponseDtoByHotel(Hotel hotel) {
        return roomRepo.findAllByHotel(hotel).stream().map(RoomResponseDto::toDto).collect(Collectors.toList());
    }

    public List<RoomResponseDto> getAllAvailableRoomResponseDto(List<Room> rooms) {
        return null;
    }
}
