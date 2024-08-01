package dev.umang.bookmyshowaug2024.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

}

/*

1            1
ShowSeat------Show => M : 1
M             1

ShowSeat-----Seat => M : 1
Show - x
Show    Seat      Status
X       1           yes
X       2           no
X       3            yes
X       3           no
Y       6
Y       2
Y       1
Z       1

 */
