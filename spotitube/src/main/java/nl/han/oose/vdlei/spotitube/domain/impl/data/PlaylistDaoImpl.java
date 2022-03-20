package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistRequest;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static nl.han.oose.vdlei.spotitube.domain.db.DbConnection.connection;

public class PlaylistDaoImpl implements PlaylistDao {
  @Inject
  TrackDaoImpl trackDao;

//  private Connection connection () {
//    return new DbConnection().connect().getConnection();
//  }

  @Override
  public int getLengthOfAllPlaylists() {
    int length = 0;
    try(Connection conn = connection()){
      PreparedStatement statement = conn.prepareStatement("SELECT SUM(TrackDuration) AS TotalDuration FROM Playlists " +
              "\tINNER JOIN Playlist_Tracks ON Playlists.PlaylistId = Playlist_Tracks.PlaylistId\n" +
              "\tINNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId \n ; ");
      ResultSet result = statement.executeQuery();
      if(result.next()) length = Integer.parseInt(result.getString("TotalDuration"));
    }catch(SQLException e){
      e.printStackTrace();
    }
    return length;
  };

  @Override
  public boolean verifyOwner(int playlistId, int userId) {
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT PlaylistOwnerId FROM Playlists WHERE PlaylistId = ?");
      statement.setInt(1, playlistId);
      ResultSet result = statement.executeQuery();
      if(result.next()){
        return Integer.parseInt(result.getString("PlaylistOwnerId")) == userId;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public ArrayList<PlaylistEntity> postNewPlaylistAndReturnAll(PlaylistRequest newPlaylist, int userId){
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("INSERT INTO Playlists(PlaylistName, PlaylistOwnerId) VALUES (?, ?)");
      statement.setString(1, newPlaylist.getName());
      statement.setInt(2, userId);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return getAllPlaylistsFromDB(userId);
  }

  @Override
  public ArrayList<PlaylistEntity> editPlaylistAndReturnAll(PlaylistRequest playlist, int userId){
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("UPDATE Playlists SET PlaylistName = ? WHERE PlaylistId = ?");
      statement.setString(1, playlist.getName());
      statement.setInt(2, playlist.getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return getAllPlaylistsFromDB(userId);
  }

  @Override
  public ArrayList<PlaylistEntity> deletePlaylistAndReturnRemaining (int playlistId, int userId){
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("DELETE FROM Playlists WHERE PlaylistId = ?");
      statement.setInt(1, playlistId);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return getAllPlaylistsFromDB(userId);
  }

  @Override
  public ArrayList<TrackEntity> getTracksFromPlaylistId (int playlistId) {
    ArrayList<TrackEntity> tracks = new ArrayList<>();
    try (Connection conn = connection()) {

  //        get tracks for each playlist
      PreparedStatement playlist_tracks_statement = conn.prepareStatement("SELECT * FROM Playlist_tracks INNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId WHERE Playlist_Tracks.PlaylistId = ?");
      playlist_tracks_statement.setInt(1, playlistId);
      ResultSet playlist_tracks_results = playlist_tracks_statement.executeQuery();

  //        loop over all tracks in a playlist
      while(playlist_tracks_results.next()) {
        tracks.add(trackDao.getTrackInformationWithinPlaylist(Integer.parseInt(playlist_tracks_results.getString("TrackId")), playlistId));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tracks;
  }

  @Override
  public ArrayList<PlaylistEntity> getAllPlaylistsFromDB(int userId) {
//    PlaylistResponse playlists = new PlaylistResponse();
    ArrayList<PlaylistEntity> playlists = new ArrayList<>();

    try(Connection conn = connection()) {
      PreparedStatement user_playlists_statement = conn.prepareStatement("SELECT * FROM Playlists");
//      user_playlists_statement.setInt(1, userId);
      ResultSet user_playlists_results = user_playlists_statement.executeQuery();

//      loop over all user's saved playlists;
      while (user_playlists_results.next()) {
        PlaylistEntity playlist = new PlaylistEntity();
        int playlistId = Integer.parseInt(user_playlists_results.getString("PlaylistId"));

//        add values
        playlist.setId(playlistId);
        playlist.setName(user_playlists_results.getString("PlaylistName"));
        playlist.setOwner(Integer.parseInt(user_playlists_results.getString("PlaylistOwnerId")) == userId);

        ArrayList<TrackEntity> tracks = getTracksFromPlaylistId(playlistId);
        for(TrackEntity track: tracks) {
          playlist.addTrack(track);
        }
//        add playlist to list to be returned;
//        playlists.addPlaylist(playlist);
        playlists.add(playlist);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return playlists;
  }

  @Override
  public void addTrackToPlaylist(int playlistId, TrackEntity track) {
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("INSERT INTO Playlist_Tracks (PlaylistId, TrackId, TrackOfflineAvailableInPlaylist) " +
              "VALUES(?, ?, ?)");
      statement.setInt(1, playlistId);
      statement.setInt(2, track.getId());
      statement.setInt(3, track.isOfflineAvailable() ? 1 : 0);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeTrackFromPlaylist(int playlistId, int trackId){
    try (Connection conn = connection()) {
      PreparedStatement statement = conn.prepareStatement("DELETE FROM Playlist_Tracks WHERE PlaylistId = ? AND TrackId = ?");
      statement.setInt(1, playlistId);
      statement.setInt(2, trackId);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
