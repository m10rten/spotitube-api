package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.service.TrackService;

import javax.inject.Inject;

public class TrackServiceImpl implements TrackService {
  private TrackDao trackDao;

  @Inject
  public void setTrackDao(TrackDao trackDao) {
    this.trackDao = trackDao;
  }

  @Override
  public TracksResponse findAllTracksNotInThePlaylist(int playlistId) {
    TracksResponse tracks = new TracksResponse();
    tracks.setTracks(trackDao.getTracksNotInPlaylistFromDB(playlistId));
    return tracks;
  }
}
