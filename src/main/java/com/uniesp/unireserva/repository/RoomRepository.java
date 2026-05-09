package com.uniesp.unireserva.repository;

import com.uniesp.unireserva.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}