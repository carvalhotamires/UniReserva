package com.uniesp.unireserva.repository;

import com.uniesp.unireserva.entity.Reservation;
import com.uniesp.unireserva.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomAndReservationDate(Room room, LocalDate reservationDate);

    List<Reservation> findByUserId(Long userId);

    boolean existsByRoomAndReservationDateAndStartTimeLessThanAndEndTimeGreaterThan(
            Room room,
            LocalDate reservationDate,
            LocalTime endTime,
            LocalTime startTime
    );
}