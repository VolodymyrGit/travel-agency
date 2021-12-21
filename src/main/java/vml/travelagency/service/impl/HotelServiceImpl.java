package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.Hotel;
import vml.travelagency.repository.HotelRepo;
import vml.travelagency.service.HotelService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

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
}
