package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class PlaylistDaoImplTest {

  @InjectMocks
  @Mock
  private PlaylistDao sut;

  @Mock
  private TrackDao trackDao;

  private static final int USER_ID = 1;
  private static final int PLAYLIST_ID = 1;

  @BeforeEach
  void setUp() {
    this.sut = new PlaylistDaoImpl();
    this.sut.setTrackDao(trackDao);
  }

  @Test
  void getLengthOfAllPlaylists() {

  }

  @Test
  void verifyOwnerToBeTruthy() {

  }

  @Test
  void verifyOwnerToBeFalsy() {
    // when(sut.verifyOwner(anyInt(), anyInt())).thenReturn(false);
    // boolean act = sut.verifyOwner(2, 3);
    // assertEquals(act, false);
  }

  @Test
  void postNewPlaylistAndReturnAll() {
  }

  @Test
  void editPlaylistAndReturnAll() {
  }

  @Test
  void deletePlaylistAndReturnRemaining() {
  }

  @Test
  void getTracksFromPlaylistId() {
  }

  @Test
  void getAllPlaylistsFromDB() {
  }
}