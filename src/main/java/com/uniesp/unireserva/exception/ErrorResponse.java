package com.uniesp.unireserva.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse  {
    private LocalDateTime timestamp; // quando aconteceu
    private Integer status; // código HTTP (400, 404, 409...)
    private String error;// "Validation Error", "Business Error", etc.
    private String message; // mensagem detalhada para o cliente
    private String path;  // endpoint chamado (/api/reservations)
    private Map<String, String> fieldErrors; // o que seria opcional, para validação
}