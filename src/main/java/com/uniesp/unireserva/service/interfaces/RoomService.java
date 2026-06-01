package com.uniesp.unireserva.service.interfaces;

import com.uniesp.unireserva.dto.request.RoomRequestDTO;
import com.uniesp.unireserva.dto.response.RoomResponseDTO;

import java.util.List;

public interface RoomService {
    RoomResponseDTO create(RoomRequestDTO dto);
    List<RoomResponseDTO> findAll();
    RoomResponseDTO findById(Long id);
    RoomResponseDTO update(Long id, RoomRequestDTO dto);
    RoomResponseDTO partialUpdate(Long id, RoomRequestDTO dto);
    void delete(Long id);
}
