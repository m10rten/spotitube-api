package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.impl.data.PlaylistDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.TrackDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistRequest;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

import javax.inject.Inject;

public class PlaylistServiceImpl implements PlaylistService {

  private PlaylistDaoImpl playlistDao;

  private UserDaoImpl userDao;

  private TrackDaoImpl trackDao;

  @Inject
  public void setPlaylistDao(PlaylistDaoImpl playlistDao) {
    this.playlistDao = playlistDao;
  }

  @Inject
  public void setUserDao(UserDaoImpl userDao) {
    this.userDao = userDao;
  }

  @Inject
  public void setTrackDao(TrackDaoImpl trackDao) {
    this.trackDao = trackDao;
  }

  @Override
  public PlaylistResponse findAllPlaylists(String token) {
    PlaylistResponse response = new PlaylistResponse();
    int userId = userDao.getId(token);
    response.setPlaylists(playlistDao.getAllPlaylistsFromDB(userId));
    response.setLength(playlistDao.getLengthOfAllPlaylists());
    return response;
  }

  @Override
  public TracksResponse getTracksFromPlaylist(int playlistId) {
    TracksResponse tracksResponse = new TracksResponse();
    tracksResponse.setTracks(playlistDao.getTracksFromPlaylistId(playlistId));
    return tracksResponse;
  }

  @Override
  public PlaylistResponse deletePlaylistWithId(int playlistId, String token) {
    PlaylistResponse playlistResponse = new PlaylistResponse();
    int userId = userDao.getId(token);
    playlistResponse.setPlaylists(playlistDao.deletePlaylistAndReturnRemaining(playlistId, userId));
    playlistResponse.setLength(playlistDao.getLengthOfAllPlaylists());
    return playlistResponse;
  }

  @Override
  public PlaylistResponse postNewPlaylistAndReturnAll(String token, PlaylistRequest newPlaylist) {
    PlaylistResponse playlistResponse = new PlaylistResponse();
    int userId = userDao.getId(token);
    playlistResponse.setPlaylists(playlistDao.postNewPlaylistAndReturnAll(newPlaylist, userId));
    playlistResponse.setLength(playlistDao.getLengthOfAllPlaylists());
    return playlistResponse;
  }

  @Override
  public PlaylistResponse editPlaylistAndReturnAllService(String token, PlaylistRequest playlist) {
    PlaylistResponse playlistResponse = new PlaylistResponse();
    int userId = userDao.getId(token);
    playlistResponse.setPlaylists(playlistDao.editPlaylistAndReturnAll(playlist, userId));
    playlistResponse.setLength(playlistDao.getLengthOfAllPlaylists());
    return playlistResponse;
  }

  @Override
  public TracksResponse postNewTrackInPlaylistService(int playlistId, TrackEntity track) {
    TracksResponse tracks = new TracksResponse();
    int trackId = track.getId();
    if (trackDao.trackExists(trackId)) {
      // add to playlist
      playlistDao.addTrackToPlaylist(playlistId, track);
    }
    tracks.setTracks(playlistDao.getTracksFromPlaylistId(playlistId));
    return tracks;
  }

  @Override
  public TracksResponse deleteTrackInPlaylistService(int playlistId, int trackId) {
    TracksResponse tracks = new TracksResponse();
    if (trackDao.trackExists(trackId)) {
      playlistDao.removeTrackFromPlaylist(playlistId, trackId);
    }
    tracks.setTracks(playlistDao.getTracksFromPlaylistId(playlistId));
    return tracks;
  }
}
