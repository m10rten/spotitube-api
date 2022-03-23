package nl.han.oose.vdlei.spotitube.domain.tracks.service;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;

public interface TrackService {
  void setTrackDao(TrackDao trackDao);

  TracksResponse findAllTracksNotInThePlaylist(int playlistId);
}
