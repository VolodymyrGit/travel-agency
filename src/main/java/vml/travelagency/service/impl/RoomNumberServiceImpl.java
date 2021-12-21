package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.model.RoomNumber;
import vml.travelagency.repository.RoomNumberRepo;
import vml.travelagency.service.RoomNumberService;

@Service
@RequiredArgsConstructor
public class RoomNumberServiceImpl implements RoomNumberService {

    private final RoomNumberRepo roomNumberRepo;

    @Override
    public RoomNumber getById(Long id) {
        return roomNumberRepo.getById(id);
    }
}
