package com.uniesp.unireserva.service.impl;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.dto.response.UserResponseDTO;
import com.uniesp.unireserva.entity.User;
import com.uniesp.unireserva.mapper.UserMapper;
import com.uniesp.unireserva.repository.UserRepository;
import com.uniesp.unireserva.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }
}