package vml.travelagency.service;

import vml.travelagency.dto.request.CountryRequestDto;
import vml.travelagency.model.Country;

public interface CountryService {

    Country create(Country country);
    Country createFromCountryRequestDto(CountryRequestDto requestDto);

    Country getCountryByName(String countryName);
}
