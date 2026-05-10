package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.LoginRequestDTO;
import com.uniesp.unireserva.dto.response.LoginResponseDTO;
import com.uniesp.unireserva.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        String token = jwtService.generateToken(dto.getEmail());

        return new LoginResponseDTO(token);
    }
}