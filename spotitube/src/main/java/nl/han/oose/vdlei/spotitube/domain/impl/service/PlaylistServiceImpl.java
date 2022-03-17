package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.db.DbConnection;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistServiceImpl implements PlaylistService {

  @Override
  public PlaylistResponse findAllPlaylists(String token) {
    PlaylistResponse response = new PlaylistResponse();
    try(Connection conn = new DbConnection().connect().getConnection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM User_Playlists WHERE UserId = ?");
    } catch (SQLException e) {

    }
    return response;
  }
}
