package nl.han.oose.vdlei.spotitube.domain;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistRequest;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import nl.han.oose.vdlei.spotitube.domain.user.data.LoginEntity;

import java.util.ArrayList;

public final class DummyData {
  public static final TrackEntity DUMMY_TRACK = makeTrack();
  public static final PlaylistEntity DUMMY_PLAYLIST = makePlaylist();
  public static final LoginEntity DUMMY_LOGIN = makeLogin();
  public static final PlaylistResponse DUMMY_PLAYLISTS = makePlaylists();
  public static final ArrayList<TrackEntity> DUMMY_TRACKS = makeTracksArraylist();
  public static final PlaylistRequest DUMMY_PLAYLIST_REQUEST = makePlaylistRequest();

  /**
   * makes a new track;
   * 
   * @return TrackEntity
   */
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
   * 
   * @return PlaylistEntity
   */
  private static PlaylistEntity makePlaylist() {
    PlaylistEntity playlist = new PlaylistEntity();
    playlist.setOwner(true);
    playlist.setName("playlistname");
    playlist.setId(1);
    playlist.setTracks(DUMMY_TRACKS);
    return playlist;
  }

  /**
   * makes a new Arraylist containing 1 playlist;
   * 
   * @return PlaylistResponse
   */
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
   * 
   * @return LoginEntity
   */
  private static LoginEntity makeLogin() {
    LoginEntity login = new LoginEntity();
    login.setToken("1234-1234-1234");
    login.setUser("username");
    return login;
  }

  /**
   * makes a new track ArrayList;
   *
   * @return ArrayList<TrackEntity>
   */
  private static ArrayList<TrackEntity> makeTracksArraylist() {
    ArrayList<TrackEntity> tracks = new ArrayList<>();
    tracks.add(makeTrack());
    return tracks;
  }

  /**
   * makes a new PlaylistRequest for incoming requests;
   *
   * @return PlaylistRequest
   */
  private static PlaylistRequest makePlaylistRequest() {
    PlaylistRequest newPlaylist = new PlaylistRequest();
    newPlaylist.setId(2);
    newPlaylist.setName("new playlist");
    return newPlaylist;
  }

}
