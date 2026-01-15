package java.ru.skypro.homework.mapper;


import ru.skypro.homework.dto.*;
import java.ru.skypro.homework.entity.AdEntity;
import java.ru.skypro.homework.entity.UserEntity;



@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    UserEntity toEntity(Register register);

    User toDto(AdEntity entity);

    ExtendedAd toExtendedAd(AdEntity adEntity);

    void updateEntityFromDto(UpdateUser updateUser, UserEntity existingUser);
}
