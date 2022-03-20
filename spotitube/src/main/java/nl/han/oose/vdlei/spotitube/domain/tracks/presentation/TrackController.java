package nl.han.oose.vdlei.spotitube.domain.tracks.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.impl.service.TrackServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

  @Inject
  UserDaoImpl userDao;

  @Inject
  TrackServiceImpl trackService;

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
  public Response getTracksNotInPlaylistEndpoint (@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
    try {
      validateToken(token);
      TracksResponse tracks = trackService.findAllTracksNotInThePlaylist(playlistId);
      return Response.status(Response.Status.OK).entity(tracks).build();
    } catch (NotAuthorizedException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
  }
}
