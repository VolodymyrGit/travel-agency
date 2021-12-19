package vml.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vml.travelagency.dto.response.CountryResponseDto;
import vml.travelagency.service.HotelService;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelsController {

    private final HotelService hotelService;

    //    @PostMapping("/add")
//    public ResponseEntity addHotel(@RequestBody @Valid HotelRequestDto hotelDto) {
//
//        return null;
//    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("incountry/find")
    public ResponseEntity<CountryResponseDto> findAllHotelsInCountry(@RequestParam String countryName) {
        CountryResponseDto countryResponseDto =
                new CountryResponseDto(hotelService.getAllHotelsByCountryName(countryName));
        return ResponseEntity.ok(countryResponseDto);
    }
}
