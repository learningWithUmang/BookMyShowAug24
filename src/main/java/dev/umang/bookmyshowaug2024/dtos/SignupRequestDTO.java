package dev.umang.bookmyshowaug2024.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String name;
    private String email;
    private String passWord;
}
