package vml.travelagency.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingAvailableRequestDto {

    private String hotelName;
    private LocalDate beginDay;
    private LocalDate endDay;
}
