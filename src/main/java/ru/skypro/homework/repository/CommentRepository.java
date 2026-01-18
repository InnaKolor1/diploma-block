package ru.skypro.homework.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByAdId(Integer adId);
    List<CommentEntity> findAllByAdIdOrderByCreatedAtDesc(Integer adId);

    CommentEntity findByIdAndAdId(Integer commentId, Integer adId);
}