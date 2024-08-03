package dev.umang.bookmyshowaug2024.dtos;

import dev.umang.bookmyshowaug2024.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}
