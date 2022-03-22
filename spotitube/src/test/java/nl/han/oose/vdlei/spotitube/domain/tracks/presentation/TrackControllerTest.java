package nl.han.oose.vdlei.spotitube.domain.tracks.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.data.TrackDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.service.TrackServiceImpl;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackControllerTest {

  private static final int TRACK_ID = 1;
  private static final String USER_TOKEN = "1234-1234-1234";
  private static final int PLAYLIST_ID = 1;

  private TrackController sut;
  private TrackServiceImpl mockedTrackService;
  private UserDaoImpl mockedUserDao;
  private TrackDaoImpl mockedTrackDao;

  @BeforeEach
  void setUp() {
    this.sut = new TrackController();

    this.mockedTrackService = mock(TrackServiceImpl.class);
    this.sut.setTrackService(mockedTrackService);

    this.mockedTrackDao = mock(TrackDaoImpl.class);
    this.mockedTrackService.setTrackDao(mockedTrackDao);

    this.mockedUserDao = mock(UserDaoImpl.class);
    this.sut.setUserDao(mockedUserDao);
  }

  @Test
  void getTracksNotInPlaylistEndpoint() {
//    arrange
    var tracks = new TracksResponse();
    ArrayList<TrackEntity> trackEntityArrayList = new ArrayList<>();
    when(mockedTrackService.findAllTracksNotInThePlaylist(PLAYLIST_ID)).thenReturn(tracks);
    when(mockedTrackDao.getTracksNotInPlaylistFromDB(PLAYLIST_ID)).thenReturn(trackEntityArrayList);
    when(mockedUserDao.verifyUserWithToken(USER_TOKEN)).thenReturn(true);
//    act
    System.out.println((this.sut.getTracksNotInPlaylistEndpoint(USER_TOKEN, PLAYLIST_ID)).toString());
    verify(mockedTrackService).findAllTracksNotInThePlaylist(TRACK_ID);

//    assert
//    assertEquals(tracks, response.getEntity());
  }
}