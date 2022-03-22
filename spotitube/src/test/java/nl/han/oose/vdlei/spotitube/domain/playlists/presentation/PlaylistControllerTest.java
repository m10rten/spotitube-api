package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.impl.data.PlaylistDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.service.PlaylistServiceImpl;
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
  private UserDaoImpl userDao;
  @Mock
  private PlaylistDaoImpl playlistDao;
  @Mock
  private PlaylistServiceImpl playlistService;

  @BeforeEach
  void setUp() {
    this.sut = new PlaylistController();

    this.userDao = mock(UserDaoImpl.class);
    sut.setUserDao(userDao);

    this.playlistDao = mock(PlaylistDaoImpl.class);
    sut.setPlaylistDao(playlistDao);

    this.playlistService = mock(PlaylistServiceImpl.class);
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