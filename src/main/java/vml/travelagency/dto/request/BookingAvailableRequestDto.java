package vml.travelagency.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingAvailableRequestDto {

    @NotBlank
    @Size(min = 3, max = 255, message = "The 'hotelName' size must be at least 3, and less than 255 characters")
    private String hotelName;
    @FutureOrPresent(message = "The 'beginDay' must be in future or present")
    private LocalDate beginDay;
    @Future(message = "The 'endDay' must be in future")
    private LocalDate endDay;
}
