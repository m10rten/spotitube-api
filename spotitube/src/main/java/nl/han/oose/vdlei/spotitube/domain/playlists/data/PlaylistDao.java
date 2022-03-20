package nl.han.oose.vdlei.spotitube.domain.playlists.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistRequest;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.util.ArrayList;

public interface PlaylistDao {
    ArrayList<PlaylistEntity> getAllPlaylistsFromDB(int userId);
    ArrayList<TrackEntity> getTracksFromPlaylistId(int playlistId);
    ArrayList<PlaylistEntity> deletePlaylistAndReturnRemaining(int playlistId, int userId);
    boolean verifyOwner(int playlistId, int userId);
    ArrayList<PlaylistEntity> postNewPlaylistAndReturnAll(PlaylistRequest newPlaylist, int userId);
    int getLengthOfAllPlaylists();
    ArrayList<PlaylistEntity> editPlaylistAndReturnAll(PlaylistRequest playlist, int userId);
    void addTrackToPlaylist(int playlistId, TrackEntity track);
    void removeTrackFromPlaylist(int playlistId, int trackId);
}
