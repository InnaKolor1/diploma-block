package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;
/**
 * REST контроллер для управления профилем пользователя.
 * Обрабатывает запросы связанные с получением и обновлением данных пользователя,
 * сменой пароля и управлением аватаром.
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @Operation(
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    /**
     * Изменяет пароль текущего аутентифицированного пользователя.
     *
     * @param newPassword объект с текущим и новым паролем
     * @return ResponseEntity со статусом 200 OK при успешной смене пароля,
     *         или 403 Forbidden при неверном текущем пароле
     */
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword,
                                            Principal principal) {
        log.info("Updating password for user: {}", principal.getName());
        userService.updatePassword(principal.getName(),
                newPassword.getCurrentPassword(),
                newPassword.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    /**
     * Обновляет информацию о текущем пользователе.
     *
     * @param updateUser объект с обновляемыми данными пользователя
     * @return ResponseEntity с обновленным объектом {@link UpdateUser} и статусом 200 OK,
     *         или 401 Unauthorized при ошибке аутентификации
     */
    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updateUser,
                                           Principal principal) {
        log.info("Updating user info for: {}", principal.getName());
        User user = userService.updateUser(principal.getName(), updateUser);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    /**
     * Получает информацию о текущем аутентифицированном пользователе.
     *
     * @return ResponseEntity с объектом {@link User} и статусом 200 OK,
     *         или 401 Unauthorized при ошибке аутентификации
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser(Principal principal) {
        log.info("Getting current user info for: {}", principal.getName());
        User user = userService.getCurrentUser(principal.getName());
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    /**
     * Обновляет аватар текущего пользователя.
     * @param image файл изображения для загрузки
     * @return ResponseEntity со статусом 200 OK при успешной загрузке,
     *         или 401 Unauthorized при ошибке аутентификации
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image,
                                                Principal principal) {
        log.info("Updating user image for: {}", principal.getName());
        try {
            String imagePath = saveUserImage(image);
            userService.updateUserImage(principal.getName(), imagePath);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            log.error("Failed to save user image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String saveUserImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String extension = originalFilename != null ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
        String filename = "user_" + UUID.randomUUID() + extension;
        Path path = Paths.get("images/" + filename);

        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());

        return filename;
    }
}
