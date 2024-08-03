package dev.umang.bookmyshowaug2024.controllers;

import dev.umang.bookmyshowaug2024.dtos.ResponseStatus;
import dev.umang.bookmyshowaug2024.dtos.SignupRequestDTO;
import dev.umang.bookmyshowaug2024.dtos.SignupResponseDTO;
import dev.umang.bookmyshowaug2024.models.User;
import dev.umang.bookmyshowaug2024.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public SignupResponseDTO signUp(SignupRequestDTO signupRequestDTO){
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();

        try{
            User user = userService.signup(signupRequestDTO.getName(),
                    signupRequestDTO.getEmail(),
                    signupRequestDTO.getPassWord());

            signupResponseDTO.setUser(user);
            signupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception ex){

            signupResponseDTO.setResponseStatus(ResponseStatus.FAILED);
        }

        return signupResponseDTO;
    }
}
