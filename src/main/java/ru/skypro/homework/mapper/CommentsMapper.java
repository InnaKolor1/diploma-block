package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CommentsMapper {


    public Comment toDto(CommentEntity commentEntity, UserEntity author) {
        if (commentEntity == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setPk(commentEntity.getId());
        comment.setText(commentEntity.getText());

        if (commentEntity.getCreatedAt() != null) {
            long epochMilli = commentEntity.getCreatedAt()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();
            comment.setCreatedAt(epochMilli);
        }

        if (author != null) {
            comment.setAuthor(author.getId());
            comment.setAuthorFirstName(author.getFirstName());
            if (author.getImage() != null && !author.getImage().isEmpty()) {
                comment.setAuthorImage("/images/" + author.getImage());
            }
        }

        return comment;
    }


    public Comment toDto(CommentEntity commentEntity) {
        return toDto(commentEntity, null);
    }


    public CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment, Integer adId) {
        if (createOrUpdateComment == null) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setAuthorId(null);
        commentEntity.setAdId(adId);
        commentEntity.setCreatedAt(LocalDateTime.now());

        return commentEntity;
    }

    public void updateEntity(CreateOrUpdateComment createOrUpdateComment, CommentEntity commentEntity) {
        if (createOrUpdateComment == null || commentEntity == null) {
            return;
        }

        if (createOrUpdateComment.getText() != null &&
                !createOrUpdateComment.getText().trim().isEmpty()) {
            commentEntity.setText(createOrUpdateComment.getText());
        }

        commentEntity.setCreatedAt(LocalDateTime.now());
    }


}