package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Comment;
import Sumurduc.Alexandru.Repository.CommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public double getAverageVote(Integer gameId) {
        Double avg = commentsRepository.getAverageVoteByGameId(gameId);
        return avg == null ? 0.0 : avg;
    }

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public void addOrUpdate(Comment comment) {
        commentsRepository.upsert(comment);
    }

    public List<Comment> getGameComments(Integer gameId) {
        return commentsRepository.findByGameId(gameId);
    }

    public boolean delete(Integer playerId, Integer gameId) {
        return commentsRepository.delete(playerId, gameId) > 0;
    }
}
