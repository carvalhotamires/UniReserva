package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{userId}")
    public ReservationResponseDTO create(
            @PathVariable Long userId,
            @RequestBody ReservationRequestDTO dto) {
        return reservationService.create(userId, dto);
    }

    @GetMapping("/user/{userId}")
    public List<ReservationResponseDTO> findByUser(@PathVariable Long userId) {
        return reservationService.findByUser(userId);
    }
}