package nl.han.oose.vdlei.spotitube.domain.tracks.presentation;

import nl.han.oose.vdlei.spotitube.domain.exceptions.InvalidTokenException;
import nl.han.oose.vdlei.spotitube.domain.tracks.service.TrackService;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

  private UserDao userDao;
  private TrackService trackService;

  @Inject
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Inject
  public void setTrackService(TrackService trackService) {
    this.trackService = trackService;
  }

  private void validateToken(String token) throws InvalidTokenException {
    boolean hasValidToken = userDao.verifyUserWithToken(token);
    if (!hasValidToken) {
      throw new InvalidTokenException("Invalid token");
    }
  }

  @Path("/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getTracksNotInPlaylistEndpoint(@QueryParam("token") String token,
      @QueryParam("forPlaylist") int playlistId) {
    try {
      validateToken(token);
      TracksResponse tracks = trackService.findAllTracksNotInThePlaylist(playlistId);
      return Response.status(Response.Status.OK).entity(tracks).build();
    } catch (InvalidTokenException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
  }
}
