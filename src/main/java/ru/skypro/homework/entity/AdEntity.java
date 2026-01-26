package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Integer price;

    private String image;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
}
