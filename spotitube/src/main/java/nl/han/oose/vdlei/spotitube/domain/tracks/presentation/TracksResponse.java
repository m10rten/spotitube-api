package nl.han.oose.vdlei.spotitube.domain.tracks.presentation;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.util.ArrayList;

public class TracksResponse {
  private ArrayList<TrackEntity> tracks = new ArrayList<>();

  public void setTracks(ArrayList<TrackEntity> tracks) {
    this.tracks = tracks;
  }

  public ArrayList<TrackEntity> getTracks() {
    return tracks;
  }
}
