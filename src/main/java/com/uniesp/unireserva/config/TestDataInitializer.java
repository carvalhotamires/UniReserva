package com.uniesp.unireserva.config;

import com.uniesp.unireserva.entity.Reservation;
import com.uniesp.unireserva.entity.Room;
import com.uniesp.unireserva.entity.User;
import com.uniesp.unireserva.enums.ReservationStatus;
import com.uniesp.unireserva.repository.ReservationRepository;
import com.uniesp.unireserva.repository.RoomRepository;
import com.uniesp.unireserva.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpar dados existentes para garantir um estado limpo para o teste
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        userRepository.deleteAll();

        // 1. Criar Usuários
        User user1 = User.builder()
                .name("Alice Silva")
                .email("alice@example.com")
                .password("senha123") // Em um projeto real, a senha seria criptografada
                .build();

        User user2 = User.builder()
                .name("Bob Santos")
                .email("bob@example.com")
                .password("senha456")
                .build();

        userRepository.saveAll(Arrays.asList(user1, user2));

        // 2. Criar Salas
        Room room1 = Room.builder()
                .name("Sala de Reunião A")
                .capacity(10)
                .location("Andar 1")
                .build();

        Room room2 = Room.builder()
                .name("Auditório Principal")
                .capacity(50)
                .location("Térreo")
                .build();

        roomRepository.saveAll(Arrays.asList(room1, room2));

        // 3. Criar Reservas
        // Reserva para hoje na Sala A por Alice
        Reservation res1 = Reservation.builder()
                .user(user1)
                .room(room1)
                .reservationDate(LocalDate.now())
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(10, 0))
                .status(ReservationStatus.CONFIRMED)
                .build();

        // Reserva para amanhã no Auditório por Bob
        Reservation res2 = Reservation.builder()
                .user(user2)
                .room(room2)
                .reservationDate(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(14, 0))
                .endTime(LocalTime.of(16, 0))
                .status(ReservationStatus.CONFIRMED)
                .build();

        // Reserva para hoje na Sala A por Bob (conflito potencial se não houver validação)
        Reservation res3 = Reservation.builder()
                .user(user2)
                .room(room1)
                .reservationDate(LocalDate.now())
                .startTime(LocalTime.of(10, 30))
                .endTime(LocalTime.of(11, 30))
                .status(ReservationStatus.CONFIRMED)
                .build();

        reservationRepository.saveAll(Arrays.asList(res1, res2, res3));

        System.out.println("Dados de teste inicializados com sucesso!");
    }
}