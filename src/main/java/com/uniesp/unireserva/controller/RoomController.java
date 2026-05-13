package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.RoomRequestDTO;
import com.uniesp.unireserva.dto.response.RoomResponseDTO;
import com.uniesp.unireserva.service.interfaces.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
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
}