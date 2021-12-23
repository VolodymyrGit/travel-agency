package vml.travelagency.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomRequestDto {

    @NotBlank(message = "The 'hotelName' must contain characters")
    @Size(min = 3, max = 255, message = "The 'hotelName' size must be at least 3, and less than 255 characters")
    private String hotelName;

    @Min(value = 1, message = "The 'roomNumber' value can't be less than 1")
    private Long roomNumber;

    @NotBlank(message = "The 'roomType' must contain characters")
    @Size(min = 3, max = 255, message = "The 'roomType' size must be at least 3, and less than 255 characters")
    private String roomType;

    @Min(value = 1, message = "The 'roomPrice' value can't be less than 1")
    private BigDecimal roomPrice;
}
