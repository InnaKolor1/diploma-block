package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Amazon.com Inc.)"
)
@Component
public class AdMapperImpl extends AdMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd) {
        if ( createOrUpdateAd == null ) {
            return null;
        }

        AdEntity adEntity = new AdEntity();

        adEntity.setTitle( createOrUpdateAd.getTitle() );
        adEntity.setPrice( createOrUpdateAd.getPrice() );
        adEntity.setDescription( createOrUpdateAd.getDescription() );

        return adEntity;
    }

    @Override
    public Ad toDto(AdEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setAuthor( entityAuthorId( entity ) );
        ad.setImage( userMapper.imageToUrl( entity.getImage() ) );
        ad.setPk( entity.getId() );
        ad.setPrice( entity.getPrice() );
        ad.setTitle( entity.getTitle() );

        return ad;
    }

    @Override
    public ExtendedAd toExtendedAd(AdEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ExtendedAd extendedAd = new ExtendedAd();

        extendedAd.setPk( entity.getId() );
        extendedAd.setAuthorFirstName( entityAuthorFirstName( entity ) );
        extendedAd.setAuthorLastName( entityAuthorLastName( entity ) );
        extendedAd.setDescription( entity.getDescription() );
        extendedAd.setEmail( entityAuthorEmail( entity ) );
        extendedAd.setImage( userMapper.imageToUrl( entity.getImage() ) );
        extendedAd.setPhone( entityAuthorPhone( entity ) );
        extendedAd.setPrice( entity.getPrice() );
        extendedAd.setTitle( entity.getTitle() );

        return extendedAd;
    }

    @Override
    public void updateEntityFromDto(CreateOrUpdateAd createOrUpdateAd, AdEntity entity) {
        if ( createOrUpdateAd == null ) {
            return;
        }

        if ( createOrUpdateAd.getTitle() != null ) {
            entity.setTitle( createOrUpdateAd.getTitle() );
        }
        if ( createOrUpdateAd.getPrice() != null ) {
            entity.setPrice( createOrUpdateAd.getPrice() );
        }
        if ( createOrUpdateAd.getDescription() != null ) {
            entity.setDescription( createOrUpdateAd.getDescription() );
        }
    }

    private Integer entityAuthorId(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        Integer id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityAuthorFirstName(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String firstName = author.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityAuthorLastName(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String lastName = author.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String entityAuthorEmail(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String email = author.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String entityAuthorPhone(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String phone = author.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }
}
