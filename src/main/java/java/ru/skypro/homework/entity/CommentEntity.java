package java.ru.skypro.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text", nullable = false, length = 64)
    private String text;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;


    public Integer getAuthorId() {
        return author.getId();
    }

    public Long getAdId() {
        return ad.getId();
    }

    public void setText(@NotBlank @Size(min = 8, max = 64) String text) {
        this.text = text;
    }

    public void setAuthorId(Integer authorId) {
    }

    public void setAdId(Integer adId) {

    }
}

