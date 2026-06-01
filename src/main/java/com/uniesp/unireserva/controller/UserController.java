package com.uniesp.unireserva.controller;

import com.uniesp.unireserva.dto.request.UserRequestDTO;
import com.uniesp.unireserva.dto.response.UserResponseDTO;
import com.uniesp.unireserva.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable Long id,
            @RequestBody UserRequestDTO dto) {
        System.out.println("PUT chamado para usuário " + id);

        return userService.update(id, dto);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO partialUpdate(
            @PathVariable Long id,
            @RequestBody UserRequestDTO dto) {
        return userService.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}