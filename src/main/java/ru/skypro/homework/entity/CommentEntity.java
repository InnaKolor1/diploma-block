package ru.skypro.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
/**
 * JPA сущность для представления комментария в базе данных.
 * Соответствует таблице 'comments' в базе данных.
 * Содержит информацию о комментарии и его связи с пользователем и объявлением.
 */
@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    /**
     * Уникальный идентификатор комментария.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Текст комментария.
     * Не может быть null, максимальная длина 64 символа.
     */
    @Column(name = "text", nullable = false, length = 64)
    private String text;
    /**
     * Дата и время создания комментария.
     * Не может быть null, хранится как временная метка.
     */

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    /**
     * Автор комментария.
     * Многосторонняя связь Many-to-One с сущностью UserEntity.
     * Не может быть null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    /**
     * Объявление, к которому относится комментарий.
     * Многосторонняя связь Many-to-One с сущностью AdEntity.
     * Не может быть null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;



    public Integer getAuthorId() {
        return author.getId();
    }

    public Integer getAdId() {
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

