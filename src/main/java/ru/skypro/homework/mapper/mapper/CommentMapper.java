package ru.skypro.homework.mapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorImage", ignore = true)
    @Mapping(target = "authorFirstName", ignore = true)
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "ad.id", target = "adId")
    Comment toDto(CommentEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    CommentEntity toEntity(CreateOrUpdateComment dto);

    void updateFromDto(CreateOrUpdateComment dto, CommentEntity comment);
}
