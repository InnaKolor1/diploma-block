package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(Integer adId) {
        List<CommentEntity> commentEntities = commentRepository.findAllByAd_IdOrderByCreatedAtDesc(adId);
        Comments comments = new Comments();
        comments.setCount(commentEntities.size());
        comments.setResults(commentEntities.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList()));
        return comments;
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setAd(ad);
        commentEntity.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, String username) {
        CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(commentId, adId)
                .orElseThrow(() -> new RuntimeException("Comment not found or doesn't belong to this ad"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!commentEntity.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Not enough permissions to delete comment");
        }

        commentRepository.delete(commentEntity);
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String username) {
        CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(commentId, adId)
                .orElseThrow(() -> new RuntimeException("Comment not found or doesn't belong to this ad"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!commentEntity.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Not enough permissions to edit comment");
        }

        commentMapper.map(createOrUpdateComment, commentEntity);
        CommentEntity updatedComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(updatedComment);  // Возвращайте DTO
    }

    public boolean isCommentOwner(Integer commentId, String username) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return commentEntity.getAuthor().getId().equals(user.getId());
    }
}