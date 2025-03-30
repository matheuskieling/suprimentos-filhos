package com.suprimentos.suprimentosfilhos.domain;

import com.suprimentos.suprimentosfilhos.dto.request.UserRequestDTO;
import com.suprimentos.suprimentosfilhos.dto.response.UserResponseDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date createdAt;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
    }

    public User(UUID id, String email, String password, Date createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserResponseDTO toResponseDTO() {
        return new UserResponseDTO(this.id, this.email);
    }
}
