package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.service.interfaces.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // CREATE - Criar uma reserva para um usuário
    @PostMapping("/{userId}")
    public ResponseEntity<ReservationResponseDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody ReservationRequestDTO dto) { // Adicionado @Valid

        ReservationResponseDTO response = reservationService.create(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ - Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> findAll() {
        List<ReservationResponseDTO> reservas = reservationService.findAll();
        return ResponseEntity.ok(reservas);
    }

    // READ - Buscar reservas de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> findByUser(@PathVariable Long userId) {
        List<ReservationResponseDTO> reservation = reservationService.findByUser(userId);
        return ResponseEntity.ok(reservation);
    }

    // READ - Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Long id) {
        ReservationResponseDTO reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    // UPDATE completo - PUT
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ReservationRequestDTO dto) {

        ReservationResponseDTO updated = reservationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // UPDATE parcial - PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody ReservationRequestDTO dto) {

        ReservationResponseDTO updated = reservationService.partialUpdate(id, dto);
        return ResponseEntity.ok(updated);
    }


    // DELETE - Excluir/cancelar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.delete(id); // aqui você aplica regra de negócio (ex: não apagar reserva passada)
        return ResponseEntity.noContent().build();
    }
}