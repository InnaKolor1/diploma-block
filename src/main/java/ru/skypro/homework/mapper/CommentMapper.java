package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

@Configuration
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Bean
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "adId", ignore = true)
    @Mapping(target = "text", source = "text")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    default boolean map(CreateOrUpdateComment value, CommentEntity commentEntity) {
            return true;
    }

    Object toString(CommentEntity commentEntity, UserEntity author);
}
