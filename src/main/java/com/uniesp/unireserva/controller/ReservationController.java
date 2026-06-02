package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.service.interfaces.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // Criar uma reserva para um usuário
    @PostMapping("/{userId}")
    public ResponseEntity<ReservationResponseDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody ReservationRequestDTO dto) { // Adicionado @Valid

        ReservationResponseDTO response = reservationService.create(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Buscar reservas de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> findByUser(@PathVariable Long userId) {
        List<ReservationResponseDTO> reservation = reservationService.findByUser(userId);
        return ResponseEntity.ok(reservation);
    }

    // Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Long id) {
        ReservationResponseDTO reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponseDTO> findAll() {
        return reservationService.findAll();
    }

    // Excluir/cancelar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.delete(id); // aqui você aplica regra de negócio (ex: não apagar reserva passada)
        return ResponseEntity.noContent().build();
    }
}