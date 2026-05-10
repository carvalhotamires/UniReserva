package com.uniesp.unireserva.mapper;

import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.entity.Reservation;

public class ReservationMapper {

    public static ReservationResponseDTO toResponse(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .userName(reservation.getUser().getName())
                .roomName(reservation.getRoom().getName())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .status(reservation.getStatus())
                .build();
    }
}