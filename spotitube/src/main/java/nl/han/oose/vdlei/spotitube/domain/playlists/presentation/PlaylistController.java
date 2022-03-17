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
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPlaylistsForUser(@QueryParam("token") String token){
        try {
            boolean hasValidToken = userDao.verifyUserWithToken(token);
            if(!hasValidToken) {
                throw new NotAuthorizedException("Invalid token");
            };
            PlaylistResponse playlistResponse = playlistService.findAllPlaylists(token);
            return Response.status(Response.Status.OK).entity(playlistResponse).build();
        } catch (NotAuthorizedException e) {
            System.out.println(e.getMessage());
          return Response.status(Response.Status.FORBIDDEN).entity(e).build();
        }
    }
}
