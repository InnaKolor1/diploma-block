package ru.skypro.homework.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentsMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;

    @Override
    public Comments getComments(Integer adId) {
        log.debug("Getting comments for ad id: {}", adId);

        List<CommentEntity> commentEntities = commentRepository.findByAdId(adId);

        List<Object> authorIds = commentEntities.stream()
                .map(CommentEntity::getAuthorId)
                .distinct()
                .collect(Collectors.toList());


        List<Comment> comments = commentEntities.stream()
                .map(comment -> {
                    Map<Object, UserEntity> authors = new HashMap<>();
                    for (Object user : userRepository.findAllByIdIn(authorIds)) {
                        if (authors.put(user, (UserEntity) user) != null) {
                            throw new IllegalStateException("Duplicate key");
                        }
                    }
                    UserEntity author = authors.get(comment.getAuthorId());
                    return commentsMapper.toDto(comment, author);
                })
                .collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);

        return result;
    }

    @Override
    @Transactional
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        log.debug("Adding comment to ad id: {} by user: {}", adId, username);

        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        CommentEntity commentEntity = commentsMapper.toEntity(createOrUpdateComment, adId);
        CommentEntity savedComment = commentRepository.save(commentEntity);

        return commentsMapper.toDto(savedComment, author);
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, String username) {
        log.debug("Deleting comment id: {} from ad id: {} by user: {}", commentId, adId, username);

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        commentEntity.getAdId();
        throw new EntityNotFoundException("Comment not found in ad with id: " + adId);

    }

    @Override
    @Transactional
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String username) {
        log.debug("Updating comment id: {} in ad id: {} by user: {}", commentId, adId, username);

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        if (!commentEntity.getAdId().equals(adId)) {
            throw new EntityNotFoundException("Comment not found in ad with id: " + adId);
        }

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        boolean isAuthor = commentEntity.getAuthorId().equals(user.getId());
        boolean isAdmin = "ADMIN".equals(user.getRole());

        if (!isAuthor && !isAdmin) {
            throw new AccessDeniedException("You don't have permission to update this comment");
        }

        commentsMapper.updateEntity(createOrUpdateComment, commentEntity);
        CommentEntity updatedComment = commentRepository.save(commentEntity);

        UserEntity author = userRepository.findById((Integer) commentEntity.getAuthorId())
                .orElse(null);

        return commentsMapper.toDto(updatedComment, author);
    }

    @Override
    public boolean isCommentOwner(Integer commentId, String username) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);
        if (commentEntity == null) {
            return false;
        }

        UserEntity user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            return false;
        }

        return commentEntity.getAuthorId().equals(user.getId());
    }
}