package java.ru.skypro.homework.mapper;


import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import java.ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.service.impl.UserMapper;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment);


    Comment toDto(CommentEntity entity);


    void updateEntityFromDto(CreateOrUpdateComment updateDto, CommentEntity commentEntity);
}

