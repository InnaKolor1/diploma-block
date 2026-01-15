package java.ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "ads")
@NoArgsConstructor(force = true)
public class AdEntity {
    private final String image;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(name = "price", nullable = false)
    private Integer price;

    @Setter
    @Getter
    @Column(name = "description", length = 64)
    private String description;

    @Getter
    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Getter
    @Setter
    private Integer authorId;
    @Getter
    @Setter
    private String title;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "ad_owner_id")
    private UserEntity adOwner;
    @Setter
    @Getter
    private String imagePath;

    public AdEntity(Integer ignoredId, String image, Integer price, String description, Integer authorId) {
        this.image = image;
        this.price = price;
        this.description = description;
        this.authorId = authorId;
    }

    public AdEntity(String image, Integer price, String description, Integer authorId) {
        this.image = image;
        this.price = price;
        this.description = description;
        this.authorId = authorId;
    }


    public UserEntity getAuthor() {
        return null;
    }

    public void setAuthor(UserEntity adOwner) {
        this.adOwner = adOwner;
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public Collection<Object> getImage() {
        return Collections.singleton(imagePath);
    }
}
