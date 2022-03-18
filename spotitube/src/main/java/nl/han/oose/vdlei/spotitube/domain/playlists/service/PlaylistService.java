package nl.han.oose.vdlei.spotitube.domain.playlists.service;

import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistTracksResponse;

import javax.inject.Inject;

public interface PlaylistService {
    PlaylistResponse findAllPlaylists(String token);
    PlaylistTracksResponse getTracksFromPlaylist(int playlistId);
    PlaylistResponse deletePlaylistWithId(int playlistId, String token);
}
