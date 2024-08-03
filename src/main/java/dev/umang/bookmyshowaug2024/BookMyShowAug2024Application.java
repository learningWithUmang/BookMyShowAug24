package dev.umang.bookmyshowaug2024;

import dev.umang.bookmyshowaug2024.controllers.UserController;
import dev.umang.bookmyshowaug2024.dtos.SignupRequestDTO;
import dev.umang.bookmyshowaug2024.dtos.SignupResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowAug2024Application implements CommandLineRunner {

    @Autowired
    private UserController userController;
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowAug2024Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
        signupRequestDTO.setName("Anmol");
        signupRequestDTO.setEmail("anmol@scaler.com");
        signupRequestDTO.setPassWord("sdsfjdksxc@123");
        SignupResponseDTO signupResponseDTO = userController.signUp(signupRequestDTO);
    }
}
