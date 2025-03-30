package com.suprimentos.suprimentosfilhos.controller;

import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.UserRequestDTO;
import com.suprimentos.suprimentosfilhos.dto.response.UserResponseDTO;
import com.suprimentos.suprimentosfilhos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    private ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.toResponseDTO(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = this.userService.createUser(userDTO);
        return new ResponseEntity<>(user.toResponseDTO(), HttpStatus.CREATED);
    }

    @PutMapping("update")
    private ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userDTO) {
        User user = this.userService.updateUser(userDTO);
        return new ResponseEntity<>(user.toResponseDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
