package com.uniesp.unireserva.dto.response;

import com.uniesp.unireserva.enums.RoomType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponseDTO {
    private Long id;
    private String name;
    private RoomType type;
    private Integer capacity;
}