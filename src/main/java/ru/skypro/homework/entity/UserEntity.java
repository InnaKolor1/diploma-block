package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String email;

    @Column(name = "first_name", nullable = false, length = 16)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Column(length = 18)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column()
    private String image;

    @Column(nullable = false)
    private String password;
}