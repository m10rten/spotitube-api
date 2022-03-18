package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.util.ArrayList;

// used for POST request to /playlist
public class PlaylistRequest {
  private String name;
  private ArrayList<TrackEntity> tracks;

  public String getName() {
    return name;
  }

  public ArrayList<TrackEntity> getTracks() {
    return tracks;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addTrack(TrackEntity track) {
    tracks.add(track);
  }
}
