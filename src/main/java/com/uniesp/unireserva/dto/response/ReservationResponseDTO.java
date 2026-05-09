package com.uniesp.reservasala.dto.response;

import com.uniesp.unireserva.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ReservationResponseDTO {
    private Long id;
    private String userName;
    private String roomName;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
}