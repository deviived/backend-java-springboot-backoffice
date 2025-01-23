package com.deviived.angularbackofficebackend.common.auth.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "provider")
    private String provider; // e.g., "local" or "github"

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role; // e.g., "ROLE_USER", "ROLE_ADMIN"
}
