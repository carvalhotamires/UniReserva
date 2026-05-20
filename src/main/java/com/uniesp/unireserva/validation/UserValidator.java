package com.uniesp.unireserva.validation;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.repository.UserRepository; // Import UserRepository
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Add RequiredArgsConstructor for constructor injection
public class UserValidator {

    private final UserRepository userRepository; // Inject UserRepository

    public void validateUserRequest(UserRequestDTO dto) {
        // Basic validation is handled by @jakarta.validation annotations in UserRequestDTO
        // This method can be extended for more complex business rules,
        // e.g., checking if an email already exists in the database.
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("O e-mail já está em uso.");
        }
    }
}