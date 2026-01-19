package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
@Configuration
public interface CommentMapper {
    @Bean
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "adId", source = "ad")
    @Mapping(target = "ad", source = "ad")
    CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment);


    @Bean
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    Comment toDto(CommentEntity entity, UserEntity author);


    @Bean
    void updateEntityFromDto(CreateOrUpdateComment updateDto, CommentEntity commentEntity);

}

