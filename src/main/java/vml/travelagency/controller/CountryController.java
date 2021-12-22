package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.request.CountryRequestDto;
import vml.travelagency.dto.response.CountryResponseDto;
import vml.travelagency.model.Country;
import vml.travelagency.service.CountryService;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/country")
@Slf4j
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<CountryResponseDto> createCountry(@Valid @RequestBody CountryRequestDto requestDto) {

        Country country = countryService.createFromCountryRequestDto(requestDto);
        CountryResponseDto responseDto = new CountryResponseDto(country.getName(), new ArrayList<>());
        log.info(String.format("New country with name - '%s' was created", country.getName()));
        return ResponseEntity.ok(responseDto);
    }
}

