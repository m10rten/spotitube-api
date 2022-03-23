package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.exceptions.InvalidTokenException;
import nl.han.oose.vdlei.spotitube.domain.impl.data.PlaylistDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.service.PlaylistServiceImpl;
import nl.han.oose.vdlei.spotitube.domain.playlists.data.PlaylistDao;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackEntity;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
  private PlaylistService playlistService;

  private PlaylistDao playlistDao;

  private UserDao userDao;

  @Inject
  public void setPlaylistService(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @Inject
  public void setPlaylistDao(PlaylistDao playlistDao) {
    this.playlistDao = playlistDao;
  }

  @Inject
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  private void validateToken(String token) throws InvalidTokenException {
    boolean hasValidToken = userDao.verifyUserWithToken(token);
    if (!hasValidToken) {
      throw new InvalidTokenException("Invalid token");
    };
  }

  @Path("/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getPlaylistsEndpoint(@QueryParam("token") String token) {
    try {
      validateToken(token);
      PlaylistResponse playlistResponse = playlistService.findAllPlaylists(token);
      return Response.status(Response.Status.OK).entity(playlistResponse).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postNewPlaylistEndpoint(@QueryParam("token") String token, PlaylistRequest newPlaylist) {
    try {
      validateToken(token);
      PlaylistResponse response = playlistService.postNewPlaylistAndReturnAll(token, newPlaylist);
      return Response.status(Response.Status.CREATED).entity(response).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deletePlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId) {
    try {
      if (!playlistDao.verifyOwner(playlistId, userDao.getId(token))) {
        throw new NotAuthorizedException("Not the owner");
      }
      PlaylistResponse remainingPlaylists = playlistService.deletePlaylistWithId(playlistId, token);
      return Response.status(Response.Status.OK).entity(remainingPlaylists).build();
    } catch (InvalidTokenException | NotAuthorizedException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response editPlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId,
      PlaylistRequest playlist) {
    try {
      validateToken(token);
      if (!playlistDao.verifyOwner(playlistId, userDao.getId(token))) {
        throw new InvalidTokenException("Not the owner");
      }
      PlaylistResponse playlists = playlistService.editPlaylistAndReturnAllService(token, playlist);
      return Response.status(Response.Status.OK).entity(playlists).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/tracks/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getTracksFromPlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId) {
    try {
      validateToken(token);
      TracksResponse response = playlistService.getTracksFromPlaylist(playlistId);
      return Response.status(Response.Status.OK).entity(response).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/tracks/")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postTrackInPlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId,
      TrackEntity track) {
    try {
      validateToken(token);
      TracksResponse tracks = playlistService.postNewTrackInPlaylistService(playlistId, track);
      return Response.status(Response.Status.OK).entity(tracks).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/tracks/{trackId}")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteTrackFromPlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId,
      @PathParam("trackId") int trackId) {
    try {
      validateToken(token);
      TracksResponse tracks = playlistService.deleteTrackInPlaylistService(playlistId, trackId);
      return Response.status(Response.Status.OK).entity(tracks).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
  }
}
