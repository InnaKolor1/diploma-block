package java.ru.skypro.homework.service;

import java.ru.skypro.homework.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}