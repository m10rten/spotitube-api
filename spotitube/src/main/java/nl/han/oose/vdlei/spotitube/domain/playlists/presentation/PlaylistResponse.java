package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistEntity;

import java.util.ArrayList;

public class PlaylistResponse {
    private int length;
    private ArrayList<PlaylistEntity> playlists = new ArrayList<PlaylistEntity>();

    public void setLength(int length) {
        this.length = length;
    }
    public int getLength() {
        return length;
    }
    public void addLength(int length){
        length += length;
    }
    public ArrayList<PlaylistEntity> getPlaylists() {
        return playlists;
    }
    public void addPlaylist(PlaylistEntity playlist) {
        playlists.add(playlist);
    }
}
