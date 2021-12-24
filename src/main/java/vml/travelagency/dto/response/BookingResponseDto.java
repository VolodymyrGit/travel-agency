package vml.travelagency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vml.travelagency.model.BookingPeriod;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {

    private String tenantEmail;
    private RoomResponseDto room;
    private LocalDate bookingDay;
    private LocalDate endBookingDay;
    private Boolean isActive;

    public static BookingResponseDto toDto(BookingPeriod period) {
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setTenantEmail(period.getUser().getEmail());
        responseDto.setRoom(RoomResponseDto.toDto(period.getRoom()));
        responseDto.setBookingDay(period.getBookingDay());
        responseDto.setEndBookingDay(period.getEndBookingDay());
        responseDto.setIsActive(period.getIsActive());
        return responseDto;
    }
}
