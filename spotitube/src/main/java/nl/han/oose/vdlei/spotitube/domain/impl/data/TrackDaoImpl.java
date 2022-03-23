package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static nl.han.oose.vdlei.spotitube.domain.db.DbConnection.connection;

public class TrackDaoImpl implements TrackDao {

  private TrackEntity convertResultIntoTrack(ResultSet result) {
    TrackEntity track = new TrackEntity();
    try {
      int duration = Integer.parseInt(result.getString("TrackDuration"));
      track.setTitle(result.getString("TrackTitle"));
      track.setAlbum(result.getString("TrackAlbum"));
      track.setId(Integer.parseInt(result.getString("TrackId")));
      track.setDescription(result.getString("TrackDescription"));
      track.setDuration(duration);
      track.setPerformer(result.getString("TrackPerformer"));
      track.setPlaycount(Integer.parseInt(result.getString("TrackPlayCount")));
      track.setPublicationDate(result.getString("TrackPublicationDate"));
      // 1 = true; 0 = false;
      track.setOfflineAvailable(Integer.parseInt(result.getString("TrackOfflineAvailableInPlaylist")) == 1);
    } catch (SQLException e) {

    }
    return track;
  }

  public boolean isTrackInAPlaylist(int trackId, int playlistId) {
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Playlist_Tracks " +
          "WHERE PlaylistId = ? AND TrackId = ?");
      statement.setInt(1, playlistId);
      statement.setInt(2, trackId);
      ResultSet results = statement.executeQuery();
      return results.next();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public TrackEntity getTrackInformationWithinPlaylist(int trackId, int playlistId) {
    TrackEntity track = new TrackEntity();
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Playlists " +
          "INNER JOIN Playlist_Tracks ON PLaylists.PlaylistId = Playlist_Tracks.PlaylistId " +
          "INNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId " +
          "WHERE Playlists.PlayListId = ? AND Tracks.TrackId = ? ");
      statement.setInt(1, playlistId);
      statement.setInt(2, trackId);
      ResultSet result = statement.executeQuery();
      // add values
      while (result.next()) {
        track = convertResultIntoTrack(result);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return track;
  }

  @Override
  public TrackEntity getTrackInformation(int trackId) {
    TrackEntity track = new TrackEntity();
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Tracks WHERE TrackId = ?");
      statement.setInt(1, trackId);
      ResultSet result = statement.executeQuery();
      // add values
      while (result.next()) {
        track = convertResultIntoTrack(result);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return track;
  }

  @Override
  public ArrayList<TrackEntity> getTracksNotInPlaylistFromDB(int playlistId) {
    ArrayList<TrackEntity> tracks = new ArrayList<TrackEntity>();
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement(
          "SELECT * FROM Tracks WHERE TrackId NOT IN (SELECT TrackId FROM Playlist_Tracks WHERE PlaylistId = ?)");
      statement.setInt(1, playlistId);
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        int trackId = Integer.parseInt(results.getString("TrackId"));
        TrackEntity track = getTrackInformation(trackId);
        tracks.add(track);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tracks;
  }

  @Override
  public boolean trackExists(int trackId) {
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT TrackId FROM Tracks WHERE TrackId = ?");
      statement.setInt(1, trackId);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
