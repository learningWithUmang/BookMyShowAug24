package dev.umang.bookmyshowaug2024.controllers;

import dev.umang.bookmyshowaug2024.dtos.BookMovieResponseDTO;
import dev.umang.bookmyshowaug2024.dtos.BookingMovieRequestDTO;
import dev.umang.bookmyshowaug2024.dtos.ResponseStatus;
import dev.umang.bookmyshowaug2024.models.Booking;
import dev.umang.bookmyshowaug2024.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO bookMovie(BookingMovieRequestDTO bookingMovieRequestDTO){
        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO();

        try {
            Booking booking = bookingService.bookMovie(
                    bookingMovieRequestDTO.getUserId(),
                    bookingMovieRequestDTO.getShowId(),
                    bookingMovieRequestDTO.getShowSeatIds()
            );

            bookMovieResponseDTO.setBooking(booking);
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return bookMovieResponseDTO;
        }catch (Exception ex){
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.FAILED);
            return bookMovieResponseDTO;
        }
    }
}
