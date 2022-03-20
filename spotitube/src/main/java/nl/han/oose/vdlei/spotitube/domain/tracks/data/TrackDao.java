package nl.han.oose.vdlei.spotitube.domain.tracks.data;

import java.util.ArrayList;

public interface TrackDao {
  ArrayList<TrackEntity> getTracksNotInPlaylistFromDB(int playlistId);
  boolean trackExists(int trackId);
  TrackEntity getTrackInformationWithinPlaylist(int trackId, int playlistId);
}
