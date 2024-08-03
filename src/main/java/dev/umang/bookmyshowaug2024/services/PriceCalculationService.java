package dev.umang.bookmyshowaug2024.services;

import dev.umang.bookmyshowaug2024.models.SeatType;
import dev.umang.bookmyshowaug2024.models.Show;
import dev.umang.bookmyshowaug2024.models.ShowSeat;
import dev.umang.bookmyshowaug2024.models.ShowSeatType;
import dev.umang.bookmyshowaug2024.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculationService {
    ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        /*
        Show SeatType price
        1       1       100
        1       2       200
        1       3       300
        1       4       250
        1       5       80
        1       6       1000

         */
        int totalAmount = 0;

        for(ShowSeat showSeat : showSeats){
            /*
            price of the current show seat
             */
            SeatType seatType = showSeat.getSeat().getSeatType();
            //Optional<ShowSeatType> showSeat1 = showSeatTypeRepository.findBySeatType(seatType);
            // show, seattype, price
            //totalAmount += showSeat1.get().getPrice();
            for(ShowSeatType showSeatType:showSeatTypes) {
                if (showSeatType.getSeatType() == seatType) {
                    totalAmount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return totalAmount;
    }
}

/*
List<ShowSeats>
1. showid, seat id, status
2.
3.
4.
5.

 */