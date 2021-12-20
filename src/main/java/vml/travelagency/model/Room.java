package vml.travelagency.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "room_number", nullable = false)
    private long roomNumber;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "room_prise", nullable = false)
    private BigDecimal roomPrice;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "end_booking_time")
    private LocalDateTime endBookingTime;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;
}
