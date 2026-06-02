package com.uniesp.unireserva.service.impl;

import com.uniesp.unireserva.dto.request.ReservationRequestDTO;
import com.uniesp.unireserva.dto.response.ReservationResponseDTO;
import com.uniesp.unireserva.entity.Reservation;
import com.uniesp.unireserva.entity.Room;
import com.uniesp.unireserva.entity.User;
import com.uniesp.unireserva.enums.ReservationStatus;
import com.uniesp.unireserva.exception.ConflictException;
import com.uniesp.unireserva.exception.ResourceNotFoundException;
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
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public ReservationResponseDTO create(Long userId, ReservationRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

        // Verifica se existe reserva que conflita com esta
        boolean conflict = reservationRepository
                .existsByRoomAndReservationDateAndStartTimeLessThanAndEndTimeGreaterThan(
                        room,
                        dto.getReservationDate(),
                        dto.getEndTime(),   // startTimeExistente < novoEnd
                        dto.getStartTime()  // endTimeExistente > novoStart
                );

        if (conflict) {
            throw new ConflictException("Já existe uma reserva para esta sala neste horário.");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(room)
                .reservationDate(dto.getReservationDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(ReservationStatus.CONFIRMED)
                .build();

        Reservation saved = reservationRepository.save(reservation);

        return ReservationMapper.toResponse(saved);
    }

    @Override
    public List<ReservationResponseDTO> findByUser(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    @Override
    public ReservationResponseDTO findById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));
        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public List<ReservationResponseDTO> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    @Override
    public ReservationResponseDTO update(Long id, ReservationRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

// Opcional: checar conflito ao atualizar
        boolean conflict = reservationRepository
                .existsByRoomAndReservationDateAndStartTimeLessThanAndEndTimeGreaterThan(
                        room,
                        dto.getReservationDate(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );
        if (conflict) {
            throw new ConflictException("Já existe uma reserva para esta sala neste horário.");
        }
        reservation.setRoom(room);
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());

        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toResponse(updatedReservation);
    }

    @Override
    public ReservationResponseDTO partialUpdate(Long id, ReservationRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        // Se vier roomId, troca a sala
        if (dto.getRoomId() != null) {
            Room room = roomRepository.findById(dto.getRoomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));
            reservation.setRoom(room);
        }

        if (dto.getReservationDate() != null) {
            reservation.setReservationDate(dto.getReservationDate());
        }

        if (dto.getStartTime() != null) {
            reservation.setStartTime(dto.getStartTime());
        }

        if (dto.getEndTime() != null) {
            reservation.setEndTime(dto.getEndTime());
        }

        // Se quiser, pode fazer verificação de conflito aqui também,
        // mas só quando data/hora/sala forem alteradas.

        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toResponse(updatedReservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        // Exemplo de regra:
        // if (reservation.getReservationDate().isBefore(LocalDate.now())) {
        //     throw new BusinessException("Não é possível cancelar uma reserva passada.");
        // }

        reservationRepository.delete(reservation);
    }
}