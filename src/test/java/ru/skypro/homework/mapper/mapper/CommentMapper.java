package ru.skypro.homework.mapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;


@Mapper
@Configuration
public interface CommentMapper {

    @Bean
    @Mapping(target = "entity", source = "author")
    @Mapping(target = "orElseThrow", source = "author")

    @Mapping(target = "id", source = "text")
    @Mapping(target = "createdAt", source = "text")
    @Mapping(target = "authorId", source = "text")
    @Mapping(target = "author", source = "text")
    @Mapping(target = "adId", source = "text")
    @Mapping(target = "ad", source = "text")

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    Comment Entity(CommentEntity entity, UserEntity author);



}

