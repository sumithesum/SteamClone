package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Comment;
import Sumurduc.Alexandru.Repository.CommentsRepository;
import Sumurduc.Alexandru.Services.CommentsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentsServiceTest {

    @Mock
    private CommentsRepository commentsRepository;

    @InjectMocks
    private CommentsService commentsService;

    @Test
    void getGameComments_returnsList() {
        when(commentsRepository.findByGameId(7)).thenReturn(List.of(new Comment()));
        assertEquals(1, commentsService.getGameComments(7).size());
        verify(commentsRepository, times(1)).findByGameId(7);
    }
    @Test
    void getAverageVote_returns0WhenNull() {
        when(commentsRepository.getAverageVoteByGameId(2)).thenReturn(null);
        assertEquals(0.0, commentsService.getAverageVote(2));
    }

    @Test
    void getAverageVote_returnsAvg() {
        when(commentsRepository.getAverageVoteByGameId(2)).thenReturn(0.5);
        assertEquals(0.5, commentsService.getAverageVote(2));
    }

    @Test
    void delete_returnsTrueWhenDeleted() {
        when(commentsRepository.delete(1, 2)).thenReturn(1);
        assertTrue(commentsService.delete(1, 2));
    }
}
