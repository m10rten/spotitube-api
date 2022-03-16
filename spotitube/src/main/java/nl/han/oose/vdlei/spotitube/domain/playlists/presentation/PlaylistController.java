package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import nl.han.oose.vdlei.spotitube.domain.playlists.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    @Inject
    PlaylistService playlistService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPlaylistsForUser(@QueryParam("token") String token){
        PlaylistResponse playlistResponse = playlistService.findPlaylists(token);
        return Response.status(Response.Status.OK).entity(playlistResponse.getJson()).build();
    }
}
