package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserEntity toEntity(Register register) {
        if ( register == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( register.getUsername() );
        userEntity.setFirstName( register.getFirstName() );
        userEntity.setLastName( register.getLastName() );
        userEntity.setPhone( register.getPhone() );
        userEntity.setRole( register.getRole() );
        userEntity.setPassword( register.getPassword() );

        return userEntity;
    }

    @Override
    public void updateEntityFromDto(UpdateUser updateUser, UserEntity entity) {
        if ( updateUser == null ) {
            return;
        }

        if ( updateUser.getFirstName() != null ) {
            entity.setFirstName( updateUser.getFirstName() );
        }
        if ( updateUser.getLastName() != null ) {
            entity.setLastName( updateUser.getLastName() );
        }
        if ( updateUser.getPhone() != null ) {
            entity.setPhone( updateUser.getPhone() );
        }
    }

    @Override
    public User toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setImage( imageToUrl( entity.getImage() ) );
        user.setId( entity.getId() );
        user.setEmail( entity.getEmail() );
        user.setFirstName( entity.getFirstName() );
        user.setLastName( entity.getLastName() );
        user.setPhone( entity.getPhone() );
        user.setRole( entity.getRole() );

        return user;
    }
}
