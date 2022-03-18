package nl.han.oose.vdlei.spotitube.domain.playlists.data;

import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;

import java.util.ArrayList;

public class PlaylistEntity {
    private int id;
    private String name;
    private boolean owner;
    private ArrayList<TrackEntity> tracks = new ArrayList<TrackEntity>();

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwner(boolean owner) {
        this.owner = owner;
    }
    public void addTrack(TrackEntity track) {
        tracks.add(track);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isOwner() {
        return owner;
    }
    public ArrayList<TrackEntity> getTracks() {
        return tracks;
    }
}
