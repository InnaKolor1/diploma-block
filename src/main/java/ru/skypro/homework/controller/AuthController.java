package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

/**
 * REST контроллер для аутентификации и регистрации пользователей.
 * Обрабатывает запросы на вход в систему и регистрацию новых пользователей.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Выполняет аутентификацию пользователя.
     * Использует базовую HTTP аутентификацию.
     *
     * @param login объект с учетными данными пользователя
     * @return ResponseEntity со статусом 200 OK при успешной аутентификации,
     *         или 401 Unauthorized при неверных учетных данных
     */

    @Operation(
            summary = "Авторизация пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody Login login) {
        log.info("Login attempt for user: {}", login.getUsername());
        if (authService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register объект с данными для регистрации
     * @return ResponseEntity со статусом 201 Created при успешной регистрации,
     *         или 400 Bad Request если пользователь с таким email уже существует
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody Register register) {
        log.info("Registration attempt for user: {}", register.getUsername());
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
