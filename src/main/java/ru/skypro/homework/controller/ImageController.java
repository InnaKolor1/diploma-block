package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * REST контроллер для получения изображений.
 * Обрабатывает запросы на получение файлов изображений по их именам.
 */
@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController {
    /**
     * Получает изображение по имени файла.
     * Поддерживает форматы JPEG, PNG и GIF.
     * Добавлены заголовки для предотвращения кэширования.
     *
     * @param filename имя файла изображения
     * @return массив байтов изображения с соответствующим Content-Type
     */
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            String imageDir = "images/";
            Path path = Paths.get(imageDir).resolve(filename).normalize();
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Определяем Content-Type
                String contentType = determineContentType(filename);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        // Заголовки для предотвращения кэширования
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                log.warn("Image not found or not readable: {}", filename);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error loading image: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }

    private String determineContentType(String filename) {
        if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (filename.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        } else {
            return "application/octet-stream";
        }
    }
}
