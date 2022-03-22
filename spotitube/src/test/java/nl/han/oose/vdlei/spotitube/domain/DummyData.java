package nl.han.oose.vdlei.spotitube.domain;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import nl.han.oose.vdlei.spotitube.domain.user.data.LoginEntity;

import javax.sound.midi.Track;
import java.util.ArrayList;

public class DummyData {
  public static final TrackEntity DUMMY_TRACK = makeTrack();
  public static final PlaylistEntity DUMMY_PLAYLIST = makePlaylist();
  public static final LoginEntity DUMMY_LOGIN = makeLogin();
  public static final PlaylistResponse DUMMY_PLAYLISTS = makePlaylists();

  /**
   * makes a new track;
   * @return TrackEntity
   * */
  private static TrackEntity makeTrack() {
    TrackEntity track = new TrackEntity();
    track.setTitle("title");
    track.setPerformer("performer");
    track.setId(1);
    track.setPublicationDate("1-1-01");
    track.setPlaycount(10);
    track.setDuration(100);
    track.setDescription("a track description");
    track.setAlbum("album");
    return track;
  }

  /**
   * makes a new playlist;
   * @return PlaylistEntity
   * */
  private static PlaylistEntity makePlaylist() {
    PlaylistEntity playlist = new PlaylistEntity();
    playlist.setOwner(true);
    playlist.setName("playlistname");
    playlist.setId(1);
    return playlist;
  }
  /**
   * makes a new Arraylist containing 1 playlist;
   * @return PlaylistResponse
   * */
  private static PlaylistResponse makePlaylists() {
    PlaylistResponse response = new PlaylistResponse();
    ArrayList<PlaylistEntity> playlists = new ArrayList<>();
    playlists.add(makePlaylist());
    response.setLength(200);
    response.setPlaylists(playlists);
    return response;
  }
/**
* makes a new login;
* @return LoginEntity
* */

  private static LoginEntity makeLogin() {
    LoginEntity login = new LoginEntity();
    login.setToken("1234-1234-1234");
    login.setUser("username");
    return login;
  }
}
