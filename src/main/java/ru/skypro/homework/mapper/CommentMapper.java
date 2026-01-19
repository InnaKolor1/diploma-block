package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Component
public class CommentMapper {

    public Comment toDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setPk(entity.getId());
        comment.setText(entity.getText());

        if (entity.getCreatedAt() != null) {
            comment.setCreatedAt(entity.getCreatedAt()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
        }

        if (entity.getAuthor() != null) {
            comment.setAuthor(entity.getAuthor().getId());
            comment.setAuthorFirstName(entity.getAuthor().getFirstName());
            comment.setAuthorImage(entity.getAuthor().getImage());
        }

        return comment;
    }

    public void map(CreateOrUpdateComment dto, CommentEntity entity) {
        if (dto != null && entity != null) {
            entity.setText(dto.getText());
        }
    }

    public CommentEntity toEntity(CreateOrUpdateComment dto) {
        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        return entity;



    }
}