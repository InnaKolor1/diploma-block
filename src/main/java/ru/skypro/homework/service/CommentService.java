package ru.skypro.homework.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.controller.Comments;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, AdRepository adRepository,
                          UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public Optional<Comments> getComments(Integer adId) {
        return adRepository.findById(adId)
                .map(ad -> {
                    List<Comment> comments = commentRepository.findAllByAd(ad).stream()
                            .map(commentMapper::toDto)
                            .collect(Collectors.toList());

                    Comments result = new Comments();
                    result.setCount(comments.size());
                    result.setResults(comments);
                    return result;
                });
    }

    public Optional<Comment> addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> user = userRepository.findByEmail(username);
        Optional<AdEntity> ad = adRepository.findById(adId);

        if (user.isPresent() && ad.isPresent()) {
            CommentEntity entity = commentMapper.toEntity(createOrUpdateComment);
            entity.setAd(ad.get());
            entity.setAuthor(user.get());

            CommentEntity savedEntity = commentRepository.save(entity);
            return Optional.of(commentMapper.toDto(savedEntity));
        }

        return Optional.empty();
    }

    public boolean deleteComment(Integer adId, Integer commentId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .filter(comment -> comment.getAuthor().getEmail().equals(username))
                .map(comment -> {
                    commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }

    public Optional<Comment> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment updateComment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .filter(comment -> comment.getAuthor().getEmail().equals(username))
                .map(comment -> {
                    comment.setText(updateComment.getText());
                    CommentEntity savedEntity = commentRepository.save(comment);
                    return commentMapper.toDto(savedEntity);
                });
    }
}