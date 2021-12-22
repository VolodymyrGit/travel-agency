package vml.travelagency.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryRequestDto {

    @NotBlank
    @Size(min = 3, max = 255, message = "The 'hotelName' size must be at least 3, and less than 255 characters")
    private String countryName;
}
