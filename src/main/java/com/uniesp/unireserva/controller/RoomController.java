package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.RoomRequestDTO;
import com.uniesp.unireserva.dto.response.RoomResponseDTO;
import com.uniesp.unireserva.service.interfaces.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor

public class RoomController {

    private final RoomService roomService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RoomResponseDTO create(@RequestBody RoomRequestDTO dto) {
        return roomService.create(dto);
    }

    @GetMapping
    public List<RoomResponseDTO> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public RoomResponseDTO findById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomResponseDTO update(
            @PathVariable Long id,
            @RequestBody RoomRequestDTO dto) {
        return roomService.update(id, dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomResponseDTO partialUpdate(
            @PathVariable Long id,
            @RequestBody RoomRequestDTO dto) {
        return roomService.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }
}