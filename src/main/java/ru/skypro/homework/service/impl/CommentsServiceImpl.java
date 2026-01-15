package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final AdService adService;

    @Override
    public Comments getComments(Integer adId) {
        List<CommentEntity> commentEntities = commentRepository.findByAdId(adId);
        List<Comment> comments = commentEntities.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        UserEntity author = userService.getUserEntity(username);
        AdEntity ad = adService.getAdEntity(adId);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setAuthor(author);
        commentEntity.setAd(ad);
        commentEntity.setCreatedAt(Instant.now());

        CommentEntity savedComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, String username) {
        CommentEntity commentEntity = commentRepository.findByIdAndAdId(commentId, adId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий не найден"));

        UserEntity currentUser = userService.getUserEntity(username);

        if (!commentEntity.getAuthor().equals(currentUser) &&
                !currentUser.getRole().equals(ru.skypro.homework.dto.Role.ADMIN)) {
            throw new AccessDeniedException("Нет прав для удаления этого комментария");
        }

        commentRepository.delete(commentEntity);
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String username) {
        CommentEntity commentEntity = commentRepository.findByIdAndAdId(commentId, adId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий не найден"));

        UserEntity currentUser = userService.getUserEntity(username);

        if (!commentEntity.getAuthor().equals(currentUser) &&
                !currentUser.getRole().equals(ru.skypro.homework.dto.Role.ADMIN)) {
            throw new AccessDeniedException("Нет прав для редактирования этого комментария");
        }

        commentEntity.setText(createOrUpdateComment.getText());
        CommentEntity updatedComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(updatedComment);
    }

    @Override
    public boolean isCommentOwner(Integer commentId, String username) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);
        if (commentEntity == null) return false;

        UserEntity userEntity = userService.getUserEntity(username);
        return commentEntity.getAuthor().equals(userEntity);
    }
}