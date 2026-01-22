package ru.skypro.homework.mapper.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", expression = "java(entity.getAuthor().getImage() != null ? \"/images/\" + entity.getAuthor().getImage() : null)")
    @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli())")
    Comment toDto(CommentEntity entity);

    @Mapping(target = "adId", source = "text")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    void updateFromDto(CreateOrUpdateComment dto, @MappingTarget CommentEntity entity);

    @Mapping(target = "adId", source = "text")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    CommentEntity toEntity(CreateOrUpdateComment dto);
}