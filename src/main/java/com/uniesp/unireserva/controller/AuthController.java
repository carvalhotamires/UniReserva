package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.LoginRequestDTO;
import com.uniesp.unireserva.dto.response.LoginResponseDTO;
import com.uniesp.unireserva.security.CustomUserDetailsService;
import com.uniesp.unireserva.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        System.out.println("HASH NOVO:");
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println("PASSOU 1");

        System.out.println("EMAIL RECEBIDO: " + dto.getEmail());
        System.out.println("SENHA RECEBIDA: " + dto.getPassword());

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(dto.getEmail());

        System.out.println("USUÁRIO ENCONTRADO: " + userDetails.getUsername());

        System.out.println("HASH DO BANCO: " + userDetails.getPassword());

        boolean senhaCorreta = passwordEncoder.matches(
                dto.getPassword(),
                userDetails.getPassword()
        );

        System.out.println("SENHA CONFERE? " + senhaCorreta);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        System.out.println("PASSOU 2");

        String token = jwtService.generateToken(dto.getEmail());

        return new LoginResponseDTO(token);
    }
}