package ru.skypro.homework.service;

import org.springframework.security.authentication.BadCredentialsException;
import ru.skypro.homework.dto.Register;

public interface AuthService {

    boolean login(String userName, String password) throws BadCredentialsException;

    boolean register(Register register);
}