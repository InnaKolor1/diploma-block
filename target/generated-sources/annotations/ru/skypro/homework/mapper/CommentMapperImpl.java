package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class CommentMapperImpl extends CommentMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment) {
        if ( createOrUpdateComment == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setText( createOrUpdateComment.getText() );

        return commentEntity;
    }

    @Override
    public Comment toDto(CommentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setAuthor( entityAuthorId( entity ) );
        comment.setAuthorImage( userMapper.imageToUrl( entityAuthorImage( entity ) ) );
        comment.setAuthorFirstName( entityAuthorFirstName( entity ) );
        comment.setPk( entity.getId() );
        comment.setText( entity.getText() );

        comment.setCreatedAt( entity.getCreatedAt().toEpochMilli() );

        return comment;
    }

    @Override
    public void updateEntityFromDto(CreateOrUpdateComment createOrUpdateComment, CommentEntity entity) {
        if ( createOrUpdateComment == null ) {
            return;
        }

        if ( createOrUpdateComment.getText() != null ) {
            entity.setText( createOrUpdateComment.getText() );
        }
    }

    private Integer entityAuthorId(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }
        UserEntity author = commentEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        Integer id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityAuthorImage(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }
        UserEntity author = commentEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String image = author.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String entityAuthorFirstName(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }
        UserEntity author = commentEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String firstName = author.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }
}
