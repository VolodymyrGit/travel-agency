package vml.travelagency.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Entity
@Table(name = "booking_period")
@Getter
@Setter
public class BookingPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Temporal(TemporalType.DATE)
    @Column(name = "booking_day")
    private LocalDate bookingDay;

//    @Temporal(TemporalType.DATE)
    @Column(name = "end_booking_day")
    private LocalDate endBookingDay;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
