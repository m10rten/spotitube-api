package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.db.DbConnection;
import nl.han.oose.vdlei.spotitube.domain.impl.data.PlaylistDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistTracksResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistServiceImpl implements PlaylistService {

  @Inject
  PlaylistDaoImpl playlistDao;

  @Inject
  UserDaoImpl userDao;

  @Override
  public PlaylistResponse findAllPlaylists(String token) {
    PlaylistResponse response = new PlaylistResponse();
    int userId = userDao.getId(token);
    response.setPlaylists(playlistDao.getAllPlaylistsWithTracksForUser(userId));
    response.setLength(playlistDao.getLengthOfAllPlaylistsFromUserId(userId));
    return response;
  }

  @Override
  public PlaylistTracksResponse getTracksFromPlaylist(int playlistId) {
    PlaylistTracksResponse tracksResponse = new PlaylistTracksResponse();
    tracksResponse.setTracks(playlistDao.getTracksFromPlaylistId(playlistId));
    return tracksResponse;
  }

  @Override
  public PlaylistResponse deletePlaylistWithId(int playlistId, String token){
    PlaylistResponse playlistResponse = new PlaylistResponse();
    int userId = userDao.getId(token);
    playlistResponse.setPlaylists(playlistDao.deletePlaylistAndReturnRemaining(playlistId, userId));
    playlistResponse.setLength(playlistDao.getLengthOfAllPlaylistsFromUserId(userId));
    return playlistResponse;
  }
}
