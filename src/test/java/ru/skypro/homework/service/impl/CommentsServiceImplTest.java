package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentsServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private UserService userService;

    @Mock
    private AdService adsService;

    @InjectMocks
    private CommentServiceImpl commentsService;

    @Test
    void getComments_ShouldReturnComments_WhenCommentsExist() {
        Integer adId = 1;
        CommentEntity commentEntity1 = new CommentEntity();
        CommentEntity commentEntity2 = new CommentEntity();
        List<CommentEntity> commentEntities = Arrays.asList(commentEntity1, commentEntity2);

        Comments result = new Comments();
        result.setCount(2);
        Comment comment1 = new Comment();
        comment1.setPk(1);
        Comment comment2 = new Comment();
        comment2.setPk(2);

        when(commentRepository.findAllByAdIdOrderByCreatedAtDesc(adId)).thenReturn(commentEntities);
        UserEntity author = new UserEntity();
        author.setFirstName("No");
        author.setLastName("Name");
        when(commentMapper.toDto(commentEntity1)).thenReturn(comment1);
        when(commentMapper.toDto(commentEntity2)).thenReturn(comment2);


        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertEquals(2, result.getResults().size());
        verify(commentRepository, times(1)).findAllByAdIdOrderByCreatedAtDesc(adId);
    }

    @Test
    void addComment_ShouldSaveComment() {
        Integer adId = 1;
        String username = "user@example.com";
        CreateOrUpdateComment commentDto = new CreateOrUpdateComment();
        commentDto.setText("Test comment");

        UserEntity author = new UserEntity();

        AdEntity ad = new AdEntity();

        CommentEntity commentEntity = new CommentEntity();

        Comment expectedComment = new Comment();
        expectedComment.setPk(1);

        when(userService.getUserEntity(username)).thenReturn(author);
        when(adsService.getAdEntity(adId)).thenReturn(ad);
        when(commentMapper.toEntity(any())).thenReturn(commentEntity);
        when(commentRepository.save(any(CommentEntity.class))).thenReturn(commentEntity);

        Comment result = commentsService.addComment(adId, commentDto, username);

        assertNotNull(result);
        assertEquals(1, result.getPk());
        verify(commentRepository, times(1)).save(any(CommentEntity.class));
        assertNotNull(commentEntity.getCreatedAt());
    }

    @Test
    void deleteComment_ShouldDeleteComment_WhenUserIsOwner() {
        Integer adId = 1;
        Integer commentId = 1;
        String username = "owner@example.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setRole(Role.USER);

        UserEntity commentAuthor = new UserEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(commentAuthor);

        when(userService.getUserEntity(username)).thenReturn(userEntity);

        assertThrows(SecurityException.class, () -> {
            commentsService.deleteComment(adId, commentId, username);
        });

        verify(commentRepository, never()).delete(any());
    }

    @Test
    void deleteComment_ShouldThrowSecurityException_WhenUserIsNotOwner() {
        Integer adId = 1;
        Integer commentId = 1;
        String username = "notowner@example.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setRole(Role.USER);

        UserEntity commentAuthor = new UserEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(commentAuthor);

        when(userService.getUserEntity(username)).thenReturn(userEntity);
        when(commentRepository.findByIdAndAdId(commentId, adId)).thenReturn(null);

        assertThrows(SecurityException.class, () -> {
            commentsService.deleteComment(adId, commentId, username);
        });

        verify(commentRepository, never()).delete(any());
    }

    @Test
    void updateComment_ShouldUpdateComment_WhenUserIsOwner() {
        Integer adId = 1;
        Integer commentId = 1;
        String username = "owner@example.com";
        CreateOrUpdateComment updateDto = new CreateOrUpdateComment();
        updateDto.setText("Updated comment");

        UserEntity userEntity = new UserEntity();

        UserEntity commentAuthor = new UserEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(commentAuthor);

        Comment expectedComment = new Comment();
        expectedComment.setPk(commentId);

        when(userService.getUserEntity(username)).thenReturn(userEntity);
        when(commentRepository.findByIdAndAdId(commentId, adId)).thenReturn(null);
        when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
        UserEntity author = new UserEntity();
        author.setFirstName("Author");
        when(commentMapper.toString(commentEntity, author)).thenReturn(expectedComment);

        Comment result = commentsService.updateComment(adId, commentId, updateDto, username);

        assertNotNull(result);
        assertEquals(commentId, result.getPk());
        verify(commentMapper, times(1)).updateCommentEntityFromDto(updateDto, commentEntity);
        verify(commentRepository, times(1)).save(commentEntity);
    }

    @Test
    void isCommentOwner_ShouldReturnTrue_WhenUserIsOwner() {
        Integer commentId = 1;
        String username = "owner@example.com";

        UserEntity userEntity = new UserEntity();

        UserEntity commentAuthor = new UserEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(commentAuthor);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
        when(userService.getUserEntity(username)).thenReturn(userEntity);

        boolean result = commentsService.isCommentOwner(commentId, username);

        assertTrue(result);
    }

    @Test
    void isCommentOwner_ShouldReturnFalse_WhenUserIsNotOwner() {
        Integer commentId = 1;
        String username = "notowner@example.com";

        UserEntity userEntity = new UserEntity();

        UserEntity commentAuthor = new UserEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(commentAuthor);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
        when(userService.getUserEntity(username)).thenReturn(userEntity);

        boolean result = commentsService.isCommentOwner(commentId, username);

        assertFalse(result);
    }
}
