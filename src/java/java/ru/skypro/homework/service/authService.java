package java.ru.skypro.homework.service;

import java.ru.skypro.homework.dto.register;

public interface authService {
    boolean login(String userName, String password);

    boolean register(register register);
}