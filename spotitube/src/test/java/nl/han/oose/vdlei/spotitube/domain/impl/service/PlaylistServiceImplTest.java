package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistServiceImplTest {

  @InjectMocks
  private PlaylistService sut;
  @Mock
  private UserDao userDao;
  @Mock
  private PlaylistDao playlistDao;
  @Mock
  private TrackDao trackDao;

  @BeforeEach
  void setUp() {
    this.sut = new PlaylistServiceImpl();

    this.trackDao = mock(TrackDao.class);
    sut.setTrackDao(trackDao);

    this.playlistDao = mock(PlaylistDao.class);
    sut.setPlaylistDao(playlistDao);

    this.userDao = mock(UserDao.class);
    sut.setUserDao(userDao);
  }

  @Test
  void findAllPlaylists() {
    var playlists = new ArrayList<PlaylistEntity>();
    playlists.add(DummyData.DUMMY_PLAYLIST);
    when(userDao.getId(DummyData.DUMMY_LOGIN.getToken())).thenReturn(1);
    when(playlistDao.getAllPlaylistsFromDB(anyInt())).thenReturn(playlists);
    when(playlistDao.getLengthOfAllPlaylists()).thenReturn(123);

    PlaylistResponse response =  this.sut.findAllPlaylists(DummyData.DUMMY_LOGIN.getToken());
    assertTrue(response instanceof PlaylistResponse);
    assertNotNull(response);
    assertEquals(123, response.getLength());
  }

  @Test
  void getTracksFromPlaylist() {
    when(playlistDao.getTracksFromPlaylistId(1)).thenReturn(DummyData.DUMMY_TRACKS);

    this.sut.getTracksFromPlaylist(1);

    verify(playlistDao).getTracksFromPlaylistId(1);
  }

  @Test
  void deletePlaylistWithId() {
    when(playlistDao.deletePlaylistAndReturnRemaining(1, 1)).thenReturn(DummyData.DUMMY_PLAYLISTS.getPlaylists());
    when(userDao.getId("1234-1234-1234")).thenReturn(1);

    PlaylistResponse response = this.sut.deletePlaylistWithId(1, "1234-1234-1234");

    verify(playlistDao).deletePlaylistAndReturnRemaining(1,1);
    assertTrue(response instanceof PlaylistResponse);
    assertNotNull(response.getPlaylists());
  }

  @Test
  void postNewPlaylistAndReturnAll() {
  }

  @Test
  void editPlaylistAndReturnAllService() {
  }

  @Test
  void postNewTrackInPlaylistService() {
  }

  @Test
  void deleteTrackInPlaylistService() {
  }
}