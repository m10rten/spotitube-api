package nl.han.oose.vdlei.spotitube.domain.tracks.presentation;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.service.TrackService;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackControllerTest {

  private static final int TRACK_ID = 1;
  private static final String USER_TOKEN = "1234-1234-1234";
  private static final int PLAYLIST_ID = 1;

  @InjectMocks
  private TrackController sut;

  @Mock
  private TrackService mockedTrackService;
  @Mock
  private UserDao mockedUserDao;
  @Mock
  private TrackDao mockedTrackDao;

  @BeforeEach
  public void setUp() {
    this.sut = new TrackController();

    this.mockedTrackService = mock(TrackService.class);
    this.sut.setTrackService(mockedTrackService);

    this.mockedTrackDao = mock(TrackDao.class);
    this.mockedTrackService.setTrackDao(mockedTrackDao);

    this.mockedUserDao = mock(UserDao.class);
    this.sut.setUserDao(mockedUserDao);
  }

  @Test
  public void getTracksNotInPlaylistFalseToken() {
    // arrange
    var tracks = new TracksResponse();
    when(mockedTrackService.findAllTracksNotInThePlaylist(PLAYLIST_ID)).thenReturn(tracks);
    when(mockedUserDao.verifyUserWithToken(USER_TOKEN)).thenReturn(false);

    // act
    Response response = this.sut.getTracksNotInPlaylistEndpoint(USER_TOKEN, PLAYLIST_ID);

    // assert
    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  public void getTrackNotInPlaylistTrueTokenAndFilled() {
//    arrange
    var tracks = new TracksResponse();
    tracks.setTracks(DummyData.DUMMY_TRACKS);
    when(mockedUserDao.verifyUserWithToken(DummyData.DUMMY_LOGIN.getToken())).thenReturn(true);
    when(mockedTrackService.findAllTracksNotInThePlaylist(DummyData.DUMMY_PLAYLIST.getId()))
            .thenReturn(tracks);

//    act
    Response response = this.sut.getTracksNotInPlaylistEndpoint(USER_TOKEN, PLAYLIST_ID);
    verify(mockedTrackService).findAllTracksNotInThePlaylist(TRACK_ID);
    
//    assert
    assertEquals(tracks, response.getEntity());
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}