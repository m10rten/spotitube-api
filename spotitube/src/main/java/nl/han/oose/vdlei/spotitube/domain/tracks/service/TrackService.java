package nl.han.oose.vdlei.spotitube.domain.tracks.service;

import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;

public interface TrackService {
  TracksResponse findAllTracksNotInThePlaylist(int playlistId);
}
