package com.uniesp.unireserva.mapper;

import com.uniesp.unireserva.dto.request.RoomRequestDTO;
import com.uniesp.unireserva.dto.response.RoomResponseDTO;
import com.uniesp.unireserva.entity.Room;

public class RoomMapper {

    public static Room toEntity(RoomRequestDTO dto) {
        return Room.builder()
                .name(dto.getName())
                .type(dto.getType())
                .capacity(dto.getCapacity())
                .location(dto.getLocation())
                .build();
    }

    public static RoomResponseDTO toResponse(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .type(room.getType())
                .capacity(room.getCapacity())
                .build();
    }
}