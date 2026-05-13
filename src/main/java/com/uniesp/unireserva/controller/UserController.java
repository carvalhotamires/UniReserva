package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.dto.response.UserResponseDTO;
import com.uniesp.unireserva.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO dto) {
        return userService.create(dto);
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}