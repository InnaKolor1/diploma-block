package ru.skypro.homework.mapper.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

class AdMapperTest {

    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Test
    void toDto_ShouldMapCorrectly() {
        UserEntity author = new UserEntity();
        author.setId(10);
        author.setUsername("test@mail.ru");

        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);
        adEntity.setTitle("Title");
        adEntity.setDescription("Description");
        adEntity.setPrice(100);
        adEntity.setAuthor(author);
        adEntity.setImage("image.jpg");

        Ad dto = adMapper.toDto(adEntity);

        assertThat(dto).isNotNull();
        assertThat(dto.getPk()).isEqualTo(1);
        assertThat(dto.getTitle()).isEqualTo("Title");
        assertThat(dto.getPrice()).isEqualTo(100);
        assertThat(dto.getAuthor()).isEqualTo(10);
        assertThat(dto.getImage()).isEqualTo("/images/image.jpg");
    }
}
