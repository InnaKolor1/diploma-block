package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getComments_ShouldReturnComments() {
        Integer adId = 12;

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAdId(1);

        when(commentRepository.findByAd_Id(adId))
                .thenReturn(List.of(commentEntity));

        commentService.getComments(adId);

        verify(commentRepository, times(1)).findByAd_Id(adId);
        verifyNoMoreInteractions(commentRepository);
    }
}
