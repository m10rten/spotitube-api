package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.playlists.presentation.PlaylistResponse;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

public class PlaylistServiceImpl implements PlaylistService {

  @Override
  public PlaylistResponse findPlaylists(String token) {
    PlaylistResponse response = new PlaylistResponse();
    return response;
  }
}
