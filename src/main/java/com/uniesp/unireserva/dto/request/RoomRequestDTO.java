package com.uniesp.unireserva.dto.request;

import com.uniesp.unireserva.enums.RoomType;
import lombok.Data;

@Data
public class RoomRequestDTO {
    private String name;
    private RoomType type;
    private String location;
    private Integer capacity;
}