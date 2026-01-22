package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getComments_ShouldReturnComments() {
        Integer adId = 1;
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1);

        when(commentRepository.findByAd_Id(adId)).thenReturn(List.of(commentEntity));

        Comments result = commentService.getComments(adId);

        assertNotNull(result);
        verify(commentRepository, times(1)).findByAd_Id(adId);
    }
}