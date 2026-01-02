package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@SuppressWarnings("MapstructReferenceInspection")
@Mapper(imports = {java.time.Instant.class})
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createAt")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "ad", source = "ad")
    default CommentEntity toEntity(CreateOrUpdateComment dto) {
        return null;
    }

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli())")
    Comment toDto(CommentEntity entity);
}