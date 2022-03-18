package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static nl.han.oose.vdlei.spotitube.domain.db.DbConnection.connection;

public class PlaylistDaoImpl implements PlaylistDao {

//  private Connection connection () {
//    return new DbConnection().connect().getConnection();
//  }

  @Override
  public PlaylistEntity getPlaylistContent(int playlistId) {
    return null;
  };

  public int getLengthOfAllPlaylistsFromUserId(int userId) {
    int length = 0;
    try(Connection conn = connection()){
      PreparedStatement statement = conn.prepareStatement("SELECT SUM(TrackDuration) AS TotalDuration FROM Users " +
              "INNER JOIN User_Playlists ON Users.UserId = User_Playlists.UserId \n" +
              "\tINNER JOIN Playlists ON User_Playlists.PlaylistId = Playlists.PlaylistId\n" +
              "\tINNER JOIN Playlist_Tracks ON Playlists.PlaylistId = Playlist_Tracks.PlaylistId\n" +
              "\tINNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId\n" +
              "\tWHERE Users.UserId = ?; ");
      statement.setInt(1, userId);
      ResultSet result = statement.executeQuery();
      if(result.next()) length = Integer.parseInt(result.getString("TotalDuration"));
    }catch(SQLException e){
      e.printStackTrace();
    }
    return length;
  };

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
  public ArrayList<PlaylistEntity> deletePlaylistAndReturnRemaining (int playlistId, int userId) {
    try (Connection conn = connection()) {
      verifyOwner(playlistId, userId);
      PreparedStatement statement = conn.prepareStatement("DELETE FROM Playlists WHERE PlaylistId = ?");
      statement.setInt(1, playlistId);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return getAllPlaylistsWithTracksForUser(userId);
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
        TrackEntity track = new TrackEntity();

  //          add values
        int duration = Integer.parseInt(playlist_tracks_results.getString("TrackDuration"));
        track.setTitle(playlist_tracks_results.getString("TrackTitle"));
        track.setAlbum(playlist_tracks_results.getString("TrackAlbum"));
        track.setId(Integer.parseInt(playlist_tracks_results.getString("TrackId")));
        track.setDescription(playlist_tracks_results.getString("TrackDescription"));
        track.setDuration(duration);
        track.setPerformer(playlist_tracks_results.getString("TrackPerformer"));
        track.setPlaycount(Integer.parseInt(playlist_tracks_results.getString("TrackPlayCount")));
        track.setPublicationDate(playlist_tracks_results.getString("TrackPublicationDate"));
  //          1 = true; 0 = false;
        track.setOfflineAvailable(Integer.parseInt(playlist_tracks_results.getString("TrackOfflineAvailable")) == 1);
  //          add length to total;
        tracks.add(track);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tracks;
  }

  @Override
  public ArrayList<PlaylistEntity> getAllPlaylistsWithTracksForUser(int userId) {
//    PlaylistResponse playlists = new PlaylistResponse();
    ArrayList<PlaylistEntity> playlists = new ArrayList<>();

    try(Connection conn = connection()) {
      PreparedStatement user_playlists_statement = conn.prepareStatement("SELECT * FROM User_Playlists INNER JOIN Playlists ON User_Playlists.PlaylistId = Playlists.PlaylistId WHERE UserId = ?");
      user_playlists_statement.setInt(1, userId);
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
}
