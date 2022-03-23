package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * PlaylistController: Endpoint tests.
 * */
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
//    arrange
    var playlists = DummyData.DUMMY_PLAYLISTS;
    when(playlistService.findAllPlaylists(DummyData.DUMMY_LOGIN.getToken())).thenReturn(playlists);

//    act
    Response response = sut.getPlaylistsEndpoint(DummyData.DUMMY_LOGIN.getToken());

//    assert
    assertEquals(playlists, response.getEntity());
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  void postNewPlaylistEndpoint() {
    var newPlaylist = DummyData.DUMMY_PLAYLIST_REQUEST;

    Response response = sut.postNewPlaylistEndpoint(DummyData.DUMMY_LOGIN.getToken(), newPlaylist);

    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  void deletePlaylistEndpointWhenIsOwner() {
    var toDeletePlaylistId = 24;
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(userDao.getId(token)).thenReturn(1);
    when(playlistDao.verifyOwner(toDeletePlaylistId, 1)).thenReturn(true);
    when(playlistService.deletePlaylistWithId(toDeletePlaylistId, token)).thenReturn(DummyData.DUMMY_PLAYLISTS);

    Response response = sut.deletePlaylistEndpoint(token, toDeletePlaylistId);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
  @Test
  void deletePlaylistEndpointWhenIsNotOwner() {
    var toDeletePlaylistId = 24;
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(userDao.getId(token)).thenReturn(1);
    when(playlistDao.verifyOwner(toDeletePlaylistId, 1)).thenReturn(false);
    when(playlistService.deletePlaylistWithId(toDeletePlaylistId, token)).thenReturn(DummyData.DUMMY_PLAYLISTS);

    Response response = sut.deletePlaylistEndpoint(token, toDeletePlaylistId);

    assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
  }

  @Test
  void editPlaylistEndpoint() {
    var editedPlaylist = DummyData.DUMMY_PLAYLIST_REQUEST;
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(userDao.getId(token)).thenReturn(1);
    when(playlistDao.verifyOwner(editedPlaylist.getId(), 1)).thenReturn(true);

    Response response = sut.editPlaylistEndpoint(token, editedPlaylist.getId(), editedPlaylist);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  void getTracksFromPlaylistEndpoint() {
    TracksResponse expectedResponse = new TracksResponse();
    var tracks = DummyData.DUMMY_TRACKS;
    expectedResponse.setTracks(tracks);
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(playlistService.getTracksFromPlaylist(1)).thenReturn(expectedResponse);

    Response response = sut.getTracksFromPlaylistEndpoint(token, 1);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expectedResponse, response.getEntity());
  }

  @Test
  void postTrackInPlaylistEndpoint() {
    var newTrackInPlaylist = DummyData.DUMMY_TRACK;
    TracksResponse expectedResponse = new TracksResponse();
    var tracks = DummyData.DUMMY_TRACKS;
    tracks.add(newTrackInPlaylist);
    expectedResponse.setTracks(tracks);
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(userDao.getId(token)).thenReturn(1);
    when(playlistDao.verifyOwner(1, 1)).thenReturn(true);
    when(playlistService.postNewTrackInPlaylistService(1, newTrackInPlaylist)).thenReturn(expectedResponse);

    Response response = sut.postTrackInPlaylistEndpoint(token, 1, newTrackInPlaylist);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expectedResponse, response.getEntity());
  }

  @Test
  void deleteTrackFromPlaylistEndpoint() {
    int trackId = 1;
    String token = DummyData.DUMMY_LOGIN.getToken();
    when(userDao.getId(token)).thenReturn(1);
    when(playlistDao.verifyOwner(1, 1)).thenReturn(true);
    when(playlistService.deleteTrackInPlaylistService(1, 1)).thenReturn(new TracksResponse());

    Response response = sut.deleteTrackFromPlaylistEndpoint(token, DummyData.DUMMY_PLAYLIST.getId(), trackId);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}