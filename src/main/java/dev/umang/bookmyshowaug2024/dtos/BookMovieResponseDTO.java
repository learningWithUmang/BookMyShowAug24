package dev.umang.bookmyshowaug2024.dtos;

import dev.umang.bookmyshowaug2024.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {
    private Booking booking;
    private ResponseStatus responseStatus;
}
