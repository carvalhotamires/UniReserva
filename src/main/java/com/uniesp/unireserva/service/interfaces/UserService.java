package com.uniesp.unireserva.service.interfaces;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserRequestDTO dto);
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(Long id);
    UserResponseDTO update(Long id, UserRequestDTO dto);
    UserResponseDTO partialUpdate(Long id, UserRequestDTO dto);
    void delete(Long id);
}
