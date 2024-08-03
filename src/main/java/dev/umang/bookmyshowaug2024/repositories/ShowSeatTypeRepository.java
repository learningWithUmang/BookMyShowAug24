package dev.umang.bookmyshowaug2024.repositories;

import dev.umang.bookmyshowaug2024.models.SeatType;
import dev.umang.bookmyshowaug2024.models.Show;
import dev.umang.bookmyshowaug2024.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);

    Optional<ShowSeatType> findBySeatType(SeatType seatType);

}
