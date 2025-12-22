package java.ru.skypro.homework.controller;

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

import java.io.Writer;
import java.ru.skypro.homework.dto.comment;
import java.ru.skypro.homework.dto.createOrUpdateComment;

import javax.validation.Valid;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class commentsController {

    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = comment.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<comment> getComments(@PathVariable("id") Integer id) {
        log.info("Getting comments for ad with id: {}", id);
        // TODO: Implement in service layer
        comment comments = new comment();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @Operation(
            summary = "Добавление комментария к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = comment.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<comment> addComment(@PathVariable("id") Integer id,
                                              @Valid @RequestBody createOrUpdateComment createOrUpdateComment) {
        log.info("Adding comment to ad with id: {}", id);
        // TODO: Implement in service layer
        Comment comment = new Comment() {
            @Override
            public int getEventType() {
                return 0;
            }

            @Override
            public Location getLocation() {
                return null;
            }

            @Override
            public boolean isStartElement() {
                return false;
            }

            @Override
            public boolean isAttribute() {
                return false;
            }

            @Override
            public boolean isNamespace() {
                return false;
            }

            @Override
            public boolean isEndElement() {
                return false;
            }

            @Override
            public boolean isEntityReference() {
                return false;
            }

            @Override
            public boolean isProcessingInstruction() {
                return false;
            }

            @Override
            public boolean isCharacters() {
                return false;
            }

            @Override
            public boolean isStartDocument() {
                return false;
            }

            @Override
            public boolean isEndDocument() {
                return false;
            }

            @Override
            public StartElement asStartElement() {
                return null;
            }

            @Override
            public EndElement asEndElement() {
                return null;
            }

            @Override
            public Characters asCharacters() {
                return null;
            }

            @Override
            public QName getSchemaType() {
                return null;
            }

            @Override
            public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {

            }

            @Override
            public String getText() {
                return "";
            }
        };
        return ResponseEntity.status(HttpStatus.OK).body((comment) comment);
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
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Integer commentId) {
        log.info("Deleting comment with id: {} from ad with id: {}", commentId, adId);
        // TODO: Implement in service layer
        return ResponseEntity.status(HttpStatus.OK).build();
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
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId,
                                                 @PathVariable("commentId") Integer commentId,
                                                 @Valid @RequestBody createOrUpdateComment createOrUpdateComment) {
        log.info("Updating comment with id: {} for ad with id: {}", commentId, adId);
        // TODO: Implement in service layer
        Comment comment = new Comment() {
            @Override
            public int getEventType() {
                return 0;
            }

            @Override
            public Location getLocation() {
                return null;
            }

            @Override
            public boolean isStartElement() {
                return false;
            }

            @Override
            public boolean isAttribute() {
                return false;
            }

            @Override
            public boolean isNamespace() {
                return false;
            }

            @Override
            public boolean isEndElement() {
                return false;
            }

            @Override
            public boolean isEntityReference() {
                return false;
            }

            @Override
            public boolean isProcessingInstruction() {
                return false;
            }

            @Override
            public boolean isCharacters() {
                return false;
            }

            @Override
            public boolean isStartDocument() {
                return false;
            }

            @Override
            public boolean isEndDocument() {
                return false;
            }

            @Override
            public StartElement asStartElement() {
                return null;
            }

            @Override
            public EndElement asEndElement() {
                return null;
            }

            @Override
            public Characters asCharacters() {
                return null;
            }

            @Override
            public QName getSchemaType() {
                return null;
            }

            @Override
            public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {

            }

            @Override
            public String getText() {
                return "";
            }
        };
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }
}