package com.suprimentos.suprimentosfilhos.controller;

import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.AuthRequest;
import com.suprimentos.suprimentosfilhos.dto.request.UserRequestDTO;
import com.suprimentos.suprimentosfilhos.dto.response.AuthResponse;
import com.suprimentos.suprimentosfilhos.dto.response.UserResponseDTO;
import com.suprimentos.suprimentosfilhos.infra.security.JwtUtil;
import com.suprimentos.suprimentosfilhos.infra.security.UserDetailServiceImpl;
import com.suprimentos.suprimentosfilhos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailsService, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    private ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = this.userService.createUser(userDTO);
        return new ResponseEntity<>(user.toResponseDTO(), HttpStatus.CREATED);
    }
}
