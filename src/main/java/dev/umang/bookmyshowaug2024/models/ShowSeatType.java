package dev.umang.bookmyshowaug2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeatType extends BaseModel{

    @ManyToOne
    private Show show;

    @ManyToOne
    private SeatType seatType;

    private int price;
}

/*
1                   1
ShowSeatTye         Show
M                     1

A B
AB : A / AB : B => M : 1
 */