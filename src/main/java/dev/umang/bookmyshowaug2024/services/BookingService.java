package dev.umang.bookmyshowaug2024.services;


import dev.umang.bookmyshowaug2024.exceptions.ShowNotFoundException;
import dev.umang.bookmyshowaug2024.exceptions.ShowSeatsNoLongerAvailableException;
import dev.umang.bookmyshowaug2024.exceptions.UserNotFoundException;
import dev.umang.bookmyshowaug2024.models.*;
import dev.umang.bookmyshowaug2024.repositories.ShowRepository;
import dev.umang.bookmyshowaug2024.repositories.ShowSeatRepository;
import dev.umang.bookmyshowaug2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    PriceCalculationService priceCalculationService;

    public BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            PriceCalculationService priceCalculationService
    ){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculationService = priceCalculationService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, ShowSeatsNoLongerAvailableException {

        /*
        1. Get the user object with the user id
        2. Get the show object with the show id
        3. Get all the show seat objects from the showSeatIds
        4. Check if all the seats are available.
        5. If no, then throw an exception
        5. If yes, proceed with the booking.
            1. Take a lock
            2. Mark the selected seats as blocked.
            3. Release the lock
            4. Create the booking and make the payment
            5. If payment succeeds :-
                1. Take a lock
                2. Mark the selected seats as booked.
                3. Release the lock.
                4. Return the booking ticket to the user.
            6. If payment fails / timer expires:-
                1. Take the lock
                2. Mark the seats as avaialble
                3. Release lock

         */
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            //go to the signup workflow, ask the user to registers
            throw new UserNotFoundException("User with userId : " + userId + " is not valid");
        }

        User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);

        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with showId " + showId + " is not present in the db");
        }

        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        if(showSeats.size() == 0){
            throw new RuntimeException("Please select atleast one seat to proceed");
        }


        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatsNoLongerAvailableException("Some seats are not available");
            }
        }

        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //Create the booking and move to the payment page.
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculationService.calculatePrice(showSeats,show));
        //Move to the payment page
        /*
        Save the booking in the db
        if booking succeeds => make the seats permanently booked
        if booking fails => make the seats available
         */
        return booking;
    }
}
