package com.uniesp.unireserva.validation;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationValidator {

    public void validateReservationRequest(ReservationRequestDTO dto) {
        validateReservationDate(dto.getReservationDate());
        validateReservationTimes(dto.getStartTime(), dto.getEndTime());
    }

    private void validateReservationDate(LocalDate reservationDate) {
        if (reservationDate == null || reservationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da reserva não pode ser no passado.");
        }
    }

    private void validateReservationTimes(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Os horários de início e fim da reserva são obrigatórios.");
        }
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("O horário de início deve ser anterior ao horário de término.");
        }

    }
}