package dev.umang.bookmyshowaug2024.repositories;

import dev.umang.bookmyshowaug2024.models.SeatType;
import dev.umang.bookmyshowaug2024.models.ShowSeat;
import dev.umang.bookmyshowaug2024.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

}
