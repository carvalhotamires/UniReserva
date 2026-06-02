package com.uniesp.unireserva.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequestDTO {

    @NotNull(message = "O ID da sala é obrigatório")
    private Long roomId;


    private Long userId;

    @NotNull(message = "A data da reserva é obrigatória")
    @FutureOrPresent(message = "A data da reserva não pode ser no passado")
    private LocalDate reservationDate;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime startTime;

    @NotNull(message = "O horário de fim é obrigatório")
    private LocalTime endTime;
}