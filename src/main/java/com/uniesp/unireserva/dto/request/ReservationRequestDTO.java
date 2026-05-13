package com.uniesp.unireserva.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequestDTO {
    private Long roomId;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}