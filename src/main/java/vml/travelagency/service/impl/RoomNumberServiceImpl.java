package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vml.travelagency.model.RoomNumber;
import vml.travelagency.repository.RoomNumberRepo;
import vml.travelagency.service.RoomNumberService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomNumberServiceImpl implements RoomNumberService {

    private final RoomNumberRepo roomNumberRepo;

    @Override
    public RoomNumber getByNumber(Long roomNumber) {
        return roomNumberRepo.findByNumber(roomNumber)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Can't find RoomNumber by this hotelName %s", roomNumber)));
    }
}
