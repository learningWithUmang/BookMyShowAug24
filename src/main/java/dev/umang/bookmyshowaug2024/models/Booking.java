package dev.umang.bookmyshowaug2024.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel{
    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    private int amount;

    @OneToMany
    private List<Payment> payments;
}

/*
    1       M
    Booking ShowSeat => M : M
    M       1

    1       1
    Booking User => M : 1
    M        1

    1       M
    Booking Payment => 1 : M
    1       1

    booking amount is 1000
    one payment through UPI - 200
    one payment through cc - 500
    rest paying through dc
 */
