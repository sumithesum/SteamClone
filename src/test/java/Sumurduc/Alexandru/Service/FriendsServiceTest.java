package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.FriendsRepository;
import Sumurduc.Alexandru.Services.FriendsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FriendsServiceTest {

    @Mock
    private FriendsRepository friendsRepository;

    @InjectMocks
    private FriendsService friendsService;

    @Test
    void getFriends_returnsList() {
        when(friendsRepository.findFriendsOfPlayer(1)).thenReturn(List.of(new Player()));
        assertEquals(1, friendsService.getFriends(1).size());
        verify(friendsRepository, times(1)).findFriendsOfPlayer(1);
    }

    @Test
    void deleteFriendship_returnsTrueWhenDeleted() {
        when(friendsRepository.destroyFriendShip(1, 2)).thenReturn(2);
        assertTrue(friendsService.deleteFriendship(1, 2));
    }

    @Test
    void addFriend_throwsWhenSameId() {
        assertThrows(IllegalArgumentException.class, () -> friendsService.addFriend(1, 1));
    }
}
