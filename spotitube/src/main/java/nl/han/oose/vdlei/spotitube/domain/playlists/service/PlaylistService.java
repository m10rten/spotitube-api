package nl.han.oose.vdlei.spotitube.domain.playlists.service;

import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;

import javax.inject.Inject;

public interface PlaylistService {
    PlaylistResponse findPlaylists(String token);
}
