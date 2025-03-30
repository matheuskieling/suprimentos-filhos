package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.Repository.UserRepository;
import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.UserRequestDTO;
import com.suprimentos.suprimentosfilhos.dto.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User findById(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User createUser(UserRequestDTO userDTO) {
        String hashedPassword = passwordEncoder.encode(userDTO.password());
        User user = new User(userDTO.email(), hashedPassword);
        user = this.userRepository.save(user);
        return user;
    }

    public User updateUser(UserRequestDTO userDTO) {
        User user = this.userRepository.findById(userDTO.id()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDTO.email());
        user = this.userRepository.save(user);
        return user;
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }
}
