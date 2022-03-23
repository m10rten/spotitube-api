package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistControllerTest {

  @InjectMocks
  private PlaylistController sut;
  @Mock
  private UserDao userDao;
  @Mock
  private PlaylistDao playlistDao;
  @Mock
  private PlaylistService playlistService;

  @BeforeEach
  void setUp() {
    this.sut = new PlaylistController();

    this.userDao = mock(UserDao.class);
    sut.setUserDao(userDao);

    this.playlistDao = mock(PlaylistDao.class);
    sut.setPlaylistDao(playlistDao);

    this.playlistService = mock(PlaylistService.class);
    sut.setPlaylistService(playlistService);
    when(userDao.verifyUserWithToken(DummyData.DUMMY_LOGIN.getToken())).thenReturn(true);
  }

  @Test
  void getPlaylistsEndpoint() {
  }

  @Test
  void postNewPlaylistEndpoint() {
  }

  @Test
  void deletePlaylistEndpoint() {
  }

  @Test
  void editPlaylistEndpoint() {
  }

  @Test
  void getTracksFromPlaylistEndpoint() {
  }

  @Test
  void postTrackInPlaylistEndpoint() {
  }

  @Test
  void deleteTrackFromPlaylistEndpoint() {
  }
}