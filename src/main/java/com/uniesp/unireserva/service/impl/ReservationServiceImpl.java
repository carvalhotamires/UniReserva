package com.uniesp.unireserva.service.impl;
import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.entity.Reservation;
import com.uniesp.unireserva.entity.Room;
import com.uniesp.unireserva.entity.User;
import com.uniesp.unireserva.enums.ReservationStatus;
import com.uniesp.unireserva.mapper.ReservationMapper;
import com.uniesp.unireserva.repository.ReservationRepository;
import com.uniesp.unireserva.repository.RoomRepository;
import com.uniesp.unireserva.repository.UserRepository;
import com.uniesp.unireserva.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl  implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public ReservationResponseDTO create(String email, ReservationRequestDTO dto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

// Verifica se existe reserva que conflita com esta
        boolean conflict = reservationRepository
                .existsByRoomAndReservationDateAndStartTimeLessThanAndEndTimeGreaterThan(
                        room,
                        dto.getReservationDate(),
                        dto.getEndTime(), // startTime < endTimeExistente
                        dto.getStartTime() // endTime > startTimeExistente
                );

        if (conflict) {
            throw new RuntimeException("Horário já reservado");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(room)
                .reservationDate(dto.getReservationDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(ReservationStatus.CONFIRMED)
                .build();

        return ReservationMapper.toResponse(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public List<ReservationResponseDTO> findByUser(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }
}