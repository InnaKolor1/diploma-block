package ru.skypro.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(force = true)
public class UserEntity {

    private final String image;
    private String email;
    @Setter
    private String firstName;
    private String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    private Role role;
    private @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone;
    @Setter
    private String password;

    public UserEntity(String email, String image, String firstName, String lastName) {
        this.email = email;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.USER;
    }

    public String getRole() {
            return null;
    }

    public void setEmail(@NotBlank @Size(min = 4, max = 32) String username) {
        this.email = username;
    }

    public void setLastName(@NotBlank @Size(min = 3, max = 10)  String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(@NotBlank @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone) {
        this.phone = phone;
    }

    public void setId(int i) {
    }

    public void getEmail(@NotBlank @Size(min = 4, max = 32) String username) {
        this.email = username;
    }
}