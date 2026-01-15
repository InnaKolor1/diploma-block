package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.ZoneId;

@Component
public class CommentMapper {

    public CommentEntity toEntity(CreateOrUpdateComment dto) {
        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        return entity;
    }

    public Comment toDto(CommentEntity entity) {
        Comment dto = new Comment();
        dto.setPk(entity.getId());
        dto.setText(entity.getText());
        dto.setCreatedAt(entity.getCreatedAt()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());

        if (entity.getAuthor() != null) {
            dto.setAuthor(entity.getAuthor().getId());
            dto.setAuthorFirstName(entity.getAuthor().getFirstName());
            dto.setAuthorImage(entity.getAuthor().getImage());
        }

        return dto;
    }
}