package nl.han.oose.vdlei.spotitube.domain.playlists.data;

import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.util.ArrayList;

public interface PlaylistDao {
    PlaylistEntity getPlaylistContent(int playlistId);
    ArrayList<PlaylistEntity> getAllPlaylistsWithTracksForUser(int userId);
    ArrayList<TrackEntity> getTracksFromPlaylistId(int playlistId);
    ArrayList<PlaylistEntity> deletePlaylistAndReturnRemaining(int playlistId, int userId);
}
