package com.uniesp.unireserva.service.impl;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.dto.response.UserResponseDTO;
import com.uniesp.unireserva.entity.User;
import com.uniesp.unireserva.enums.UserRole;
import com.uniesp.unireserva.exception.ResourceNotFoundException;
import com.uniesp.unireserva.mapper.UserMapper;
import com.uniesp.unireserva.repository.UserRepository;
import com.uniesp.unireserva.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    /*
    @Override
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }*/
    @Override
    public UserResponseDTO findById(Long id) {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        User loggedUser = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        boolean isAdmin = loggedUser.getRole() == UserRole.ADMIN;
        boolean isOwner = loggedUser.getId().equals(user.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException(
                    "Você não pode visualizar este usuário");
        }

        return UserMapper.toResponse(user);
    }

    /*
    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }
     */
    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        User loggedUser = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        boolean isAdmin = loggedUser.getRole() == UserRole.ADMIN;
        boolean isOwner = loggedUser.getId().equals(user.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException(
                    "Você não pode alterar este usuário");
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return UserMapper.toResponse(userRepository.save(user));
    }
/*
    @Override
    public UserResponseDTO partialUpdate(Long id, UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }*/
@Override
public UserResponseDTO partialUpdate(Long id, UserRequestDTO dto) {

    Authentication auth = SecurityContextHolder
            .getContext()
            .getAuthentication();

    User loggedUser = userRepository.findByEmail(auth.getName())
            .orElseThrow(() ->
                    new ResourceNotFoundException("Usuário não encontrado"));

    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Usuário não encontrado"));

    boolean isAdmin = loggedUser.getRole() == UserRole.ADMIN;
    boolean isOwner = loggedUser.getId().equals(user.getId());

    if (!isAdmin && !isOwner) {
        throw new AccessDeniedException(
                "Você não pode alterar este usuário");
    }

    if (dto.getName() != null) {
        user.setName(dto.getName());
    }

    if (dto.getEmail() != null) {
        user.setEmail(dto.getEmail());
    }

    if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    return UserMapper.toResponse(userRepository.save(user));
}
/*
    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        userRepository.delete(user);
    }
*/
    @Override
    public void delete(Long id) {

        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User loggedUser = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário logado não encontrado"));

        boolean isAdmin = loggedUser.getRole() == UserRole.ADMIN;
        boolean isOwner = loggedUser.getId().equals(userToDelete.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("Você não pode excluir este usuário");
        }

        userRepository.delete(userToDelete);
    }
}