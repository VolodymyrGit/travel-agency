package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vml.travelagency.dto.request.CountryRequestDto;
import vml.travelagency.exceptions.NullEntityReferenceException;
import vml.travelagency.model.Country;
import vml.travelagency.repository.CountryRepo;
import vml.travelagency.service.CountryService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;

    @Override
    public Country create(Country country) {
        if (country == null) {
            log.error("Method create, country parameter - 'null'");
            throw new NullEntityReferenceException("Method create - country parameter can't be 'null'");
        }
        return countryRepo.save(country);
    }

    @Override
    public Country createFromCountryRequestDto(CountryRequestDto requestDto) {
        Country country = Country.builder()
                .name(requestDto.getCountryName())
                .hotels(new ArrayList<>())
                .build();
        return create(country);
    }

    @Override
    public Country getCountryByName(String countryName) {
        return countryRepo.findByName(countryName)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Can't find Country with this countryName - %s", countryName)));
    }
}
