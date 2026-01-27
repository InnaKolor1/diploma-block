package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

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
        List<Comment> comments = commentRepository.findByAd_Id(adId).stream().map(commentMapper::toDto).toList();

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, String username) {

        AdEntity ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));

        UserEntity author = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        CommentEntity entity = commentMapper.toEntity(dto);
        entity.setAuthor(author);
        entity.setAd(ad);

        return commentMapper.toDto(commentRepository.save(entity));
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, String username) {

        CommentEntity comment = commentRepository.findByIdAndAd_Id(commentId, adId).orElseThrow(() -> new RuntimeException("Comment not found"));

        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (
                !comment.getAuthor().getId().equals(user.getId())
        ) {
            user.getRole();
            throw new RuntimeException("Forbidden");
        }


        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto, String username) {

        CommentEntity comment = commentRepository.findByIdAndAd_Id(commentId, adId).orElseThrow(() -> new RuntimeException("Comment not found"));

        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (
                !comment.getAuthor().getId().equals(user.getId())
        ) {
            user.getRole();
            throw new RuntimeException("Forbidden");
        }


        commentMapper.updateFromDto(dto, comment);
        return commentMapper.toDto(commentRepository.save(comment));
    }
}
