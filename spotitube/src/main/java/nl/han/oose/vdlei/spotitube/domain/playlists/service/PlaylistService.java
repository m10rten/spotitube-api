package nl.han.oose.vdlei.spotitube.domain.playlists.service;

import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistRequest;
import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;

public interface PlaylistService {
    PlaylistResponse findAllPlaylists(String token);

    TracksResponse getTracksFromPlaylist(int playlistId);

    PlaylistResponse deletePlaylistWithId(int playlistId, String token);

    PlaylistResponse postNewPlaylistAndReturnAll(String token, PlaylistRequest newPlaylist);

    PlaylistResponse editPlaylistAndReturnAllService(String token, PlaylistRequest playlist);

    TracksResponse postNewTrackInPlaylistService(int playlistId, TrackEntity track);

    TracksResponse deleteTrackInPlaylistService(int playlistId, int trackId);

    void setTrackDao(TrackDao trackDao);
    void setPlaylistDao(PlaylistDao playlistDao);
    void setUserDao(UserDao userDao);
}
