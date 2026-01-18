package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public enum Role {
    USER,
    ADMIN;

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
