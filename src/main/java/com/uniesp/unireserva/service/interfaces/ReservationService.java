package com.uniesp.unireserva.service.interfaces;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {
    ReservationResponseDTO create(Long userId, ReservationRequestDTO dto);
    List<ReservationResponseDTO> findByUser(Long userId);
}
