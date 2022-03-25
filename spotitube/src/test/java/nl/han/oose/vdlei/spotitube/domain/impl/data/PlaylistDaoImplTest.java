package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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