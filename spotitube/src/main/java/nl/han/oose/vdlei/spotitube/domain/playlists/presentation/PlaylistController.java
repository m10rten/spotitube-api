package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.service.PlaylistServiceImpl;
import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
  @Inject
  PlaylistServiceImpl playlistService;
  @Inject
  UserDaoImpl userDao;

  private void validateToken (String token) throws NotAuthorizedException {
    boolean hasValidToken = userDao.verifyUserWithToken(token);
    if(!hasValidToken) {
      throw new NotAuthorizedException("Invalid token");
    };
  }

  @Path("/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getPlaylistsForUserEndpoint(@QueryParam("token") String token){
    try {
      validateToken(token);
      PlaylistResponse playlistResponse = playlistService.findAllPlaylists(token);
      return Response.status(Response.Status.OK).entity(playlistResponse).build();
    } catch (NotAuthorizedException e) {
      e.printStackTrace();
      return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deletePlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId) {
    try {
      PlaylistResponse remainingPlaylists = playlistService.deletePlaylistWithId(playlistId, token);
      return Response.status(Response.Status.OK).entity(remainingPlaylists).build();
    } catch (NotAuthorizedException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
  }

  @Path("/{id}/tracks/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getTracksFromPlaylistEndpoint(@QueryParam("token") String token, @PathParam("id") int playlistId){
    try {
      validateToken(token);
      PlaylistTracksResponse response = playlistService.getTracksFromPlaylist(playlistId);
      return Response.status(Response.Status.OK).entity(response).build();
    } catch (NotAuthorizedException e) {
      e.printStackTrace();
      return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
  }
}
