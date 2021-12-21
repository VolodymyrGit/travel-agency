package vml.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "number_id", nullable = false)
    private RoomNumber roomNumber;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

//    @Column(name = "is_available", nullable = false)
//    private boolean isAvailable;

    @Column(name = "room_prise", nullable = false)
    private BigDecimal roomPrice;

    @OneToMany(mappedBy = "room")
    private List<BookingPeriod> bookingPeriods;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;
}
