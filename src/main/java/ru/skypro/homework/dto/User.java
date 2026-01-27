package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.Role;

import java.util.Objects;

@Getter
@Setter
public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Role setRole(Role role) {
        setRole(role);
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        setRole(role);
    }

    public void setPassword(String password) {
        setPassword(password);
    }

}
