package com.uniesp.unireserva.service.impl;
import com.uniesp.unireserva.dto.request.RoomRequestDTO;
import com.uniesp.unireserva.dto.response.RoomResponseDTO;
import com.uniesp.unireserva.entity.Room;
import com.uniesp.unireserva.mapper.RoomMapper;
import com.uniesp.unireserva.repository.RoomRepository;
import com.uniesp.unireserva.service.interfaces.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomResponseDTO create(RoomRequestDTO dto) {
        Room room = RoomMapper.toEntity(dto);
        return RoomMapper.toResponse(roomRepository.save(room));
    }

    @Override
    public List<RoomResponseDTO> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toResponse)
                .toList();
    }

    @Override
    public RoomResponseDTO findById(Long id) {
        return roomRepository.findById(id)
                .map(RoomMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }
}