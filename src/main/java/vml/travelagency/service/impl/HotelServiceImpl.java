package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.exceptions.NullMethodParameterException;
import vml.travelagency.exceptions.RoomNumberAlreadyExistException;
import vml.travelagency.model.Country;
import vml.travelagency.model.Hotel;
import vml.travelagency.repository.HotelRepo;
import vml.travelagency.service.HotelService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        if (hotel == null) {
            throw new NullEntityReferenceException("Method create - hotel parameter can't be 'null'");
        }
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel createFromCountryAndHotelRequestDto(Country country, String hotelName) {
        Hotel hotel = Hotel.builder()
                .country(country)
                .hotelName(hotelName)
                .rooms(new ArrayList<>())
                .build();
        return create(hotel);
    }

    @Override
    public Hotel getByHotelName(String hotelName) {
        return hotelRepo.findByHotelName(hotelName)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Can't find Hotel by this hotelName %s", hotelName)));
    }

    @Override
    public List<Hotel> getAllByCountryName(String countryName) {
        return hotelRepo.findAllByCountryName(countryName);
    }

    @Override
    public boolean checkIfRoomNumberNotExist(Hotel hotel, Long roomNumber) {
        if (hotel == null) {
            throw new NullEntityReferenceException("Method checkIfRoomNumberNotExist - hotel parameter can't be 'null'");
        } else if (roomNumber == null) {
            throw new NullMethodParameterException("Method checkIfRoomNumberNotExist - roomNumber can't be 'null'");
        }
        boolean exist = hotel.getRooms().stream().anyMatch(room -> Objects.equals(room.getRoomNumber(), roomNumber));
        if (exist) {
            throw new RoomNumberAlreadyExistException(roomNumber);
        }
        return false;
    }
}
