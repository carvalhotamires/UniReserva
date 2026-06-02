package com.uniesp.unireserva.service.interfaces;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {

    // Criar reserva (com regra de conflito, capacidade etc.)
    ReservationResponseDTO create(Long userId, ReservationRequestDTO dto);
    // Buscar uma reserva pelo ID
    ReservationResponseDTO findById(Long id);

    // Listar todas as reservas (para ADMIN, por exemplo)
    List<ReservationResponseDTO> findAll();

    // Listar reservas de um usuário específico
    List<ReservationResponseDTO> findByUser(Long userId);

    // (Opcional) Atualizar uma reserva - so se seu domínio permitir
    //ReservationResponseDTO update(Long id, ReservationRequestDTO dto);

    // Cancelar / deletar reserva
    void delete(Long id);
}

