package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import java.security.Principal;
/**
 * REST контроллер для управления комментариями к объявлениям.
 * Обрабатывает HTTP запросы связанные с созданием, получением, обновлением и удалением комментариев.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    /**
     * Получает все комментарии к указанному объявлению.
     *
     * @param id идентификатор объявления
     * @return ResponseEntity с объектом {@link Comments} и статусом 200 OK,
     *         или 404 Not Found если объявление не существует
     */
    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable("id") Integer id) {
        log.info("Getting comments for ad with id: {}", id);
        Comments comments = commentsService.getComments(id);
        return ResponseEntity.ok(comments);
    }

    @Operation(
            summary = "Добавление комментария к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    /**
     * Добавляет новый комментарий к объявлению.
     * Требуется аутентификация пользователя.
     *
     * @param id идентификатор объявления
     * @param comment данные комментария
     * @return ResponseEntity с созданным комментарием и статусом 200 OK,
     *         или 401 Unauthorized при ошибке аутентификации
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                              Principal principal) {
        log.info("Adding comment to ad with id: {} by user: {}", id, principal.getName());
        Comment comment = commentsService.addComment(id, createOrUpdateComment, principal.getName());
        return ResponseEntity.ok(comment);
    }

    @Operation(
            summary = "Удаление комментария",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    /**
     * Удаляет комментарий.
     * Доступно только администраторам или авторам комментария.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @return ResponseEntity со статусом 200 OK при успешном удалении,
     *         403 Forbidden при недостаточных правах,
     *         401 Unauthorized при ошибке аутентификации
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Integer commentId,
                                              Principal principal) {
        log.info("Deleting comment with id: {} from ad with id: {} by user: {}", commentId, adId, principal.getName());
        commentsService.deleteComment(adId, commentId, principal.getName());
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    /**
     * Обновляет комментарий.
     * Доступно только администраторам или авторам комментария.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param comment обновленные данные комментария
     * @return ResponseEntity с обновленным комментарием и статусом 200 OK
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId,
                                                 @PathVariable("commentId") Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                 Principal principal) {
        log.info("Updating comment with id: {} for ad with id: {} by user: {}", commentId, adId, principal.getName());
        Comment comment = commentsService.updateComment(adId, commentId, createOrUpdateComment, principal.getName());
        return ResponseEntity.ok(comment);
    }
}
