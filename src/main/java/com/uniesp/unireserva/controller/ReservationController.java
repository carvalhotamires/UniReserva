package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponseDTO create(
            @RequestBody ReservationRequestDTO dto,
            Authentication authentication) {

        String email = authentication.getName();

        return reservationService.create(email, dto);
    }

    @GetMapping("/user/{userId}")
    public List<ReservationResponseDTO> findByUser(@PathVariable Long userId) {
        return reservationService.findByUser(userId);
    }
}