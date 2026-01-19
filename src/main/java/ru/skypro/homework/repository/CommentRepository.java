package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByAd_Id(Integer adId);
    List<CommentEntity> findAllByAd_IdOrderByCreatedAtDesc(Integer adId);


    void deleteByIdAndAd_Id(Integer commentId, Integer adId);
    boolean existsByIdAndAd_Id(Integer commentId, Integer adId);

    CommentEntity findByIdAndAd_Id(Integer commentId, Integer adId);
}