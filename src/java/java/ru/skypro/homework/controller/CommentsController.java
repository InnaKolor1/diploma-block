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
import java.ru.skypro.homework.dto.Comment;
import java.ru.skypro.homework.dto.CreateOrUpdateComment;

import javax.validation.Valid;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentsController {

    @Operation(
            summary = "Получение комментариев объявления",
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
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comment> getComments(@PathVariable("id") Integer id) {
        log.info("Getting comments for ad with id: {}", id);
        // TODO: Implement in service layer
        Comment comments = new Comment();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
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
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id,
                                              @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Adding comment to ad with id: {}", id);
        // TODO: Implement in service layer
        javax.xml.stream.events.Comment comment = new javax.xml.stream.events.Comment() {
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
        return ResponseEntity.status(HttpStatus.OK).body((Comment) comment);
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
                                    schema = @Schema(implementation = javax.xml.stream.events.Comment.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<javax.xml.stream.events.Comment> updateComment(@PathVariable("adId") Integer adId,
                                                                         @PathVariable("commentId") Integer commentId,
                                                                         @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Updating comment with id: {} for ad with id: {}", commentId, adId);
        // TODO: Implement in service layer
        javax.xml.stream.events.Comment comment = new javax.xml.stream.events.Comment() {
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