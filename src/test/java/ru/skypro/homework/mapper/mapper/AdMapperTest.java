package ru.skypro.homework.mapper.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

class AdMapperTest {

    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Test
    void toDto_ShouldMapCorrectly() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");

        AdEntity entity = new AdEntity();
        entity.setId(1);
        entity.setTitle("Test Ad");
        entity.setPrice(1000);
        entity.setDescription("Test description");
        entity.setImage("image.jpg");
        entity.setAuthor(author);

        Ad result = adMapper.toDto(entity);

        assertNotNull(result);
        assertEquals(1, result.getPk());
        assertEquals("Test Ad", result.getTitle());
        assertEquals(1000, result.getPrice());
        assertEquals("/images/image.jpg", result.getImage());
        assertEquals(1, result.getAuthor());
    }

    @Test
    void toExtendedAd_ShouldMapCorrectly() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john@example.com");
        author.setPhone("+79991234567");

        AdEntity entity = new AdEntity();
        entity.setId(1);
        entity.setTitle("Test Ad");
        entity.setPrice(1000);
        entity.setDescription("Test description");
        entity.setImage("image.jpg");
        entity.setAuthor(author);

        ExtendedAd result = adMapper.toExtendedAd(entity);

        assertNotNull(result);
        assertEquals(1, result.getPk());
        assertEquals("Test Ad", result.getTitle());
        assertEquals(1000, result.getPrice());
        assertEquals("Test description", result.getDescription());
        assertEquals("/images/image.jpg", result.getImage());
        assertEquals("John", result.getAuthorFirstName());
        assertEquals("Doe", result.getAuthorLastName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("+79991234567", result.getPhone());
    }
}