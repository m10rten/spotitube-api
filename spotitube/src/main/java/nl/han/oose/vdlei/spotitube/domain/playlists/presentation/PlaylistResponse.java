package nl.han.oose.vdlei.spotitube.domain.playlists.presentation;

import javax.ws.rs.core.Response;

public class PlaylistResponse {
    private String json;
    private Response.Status status = Response.Status.NOT_FOUND;

    public void setJson(String json) {
        this.json = json;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public String getJson() {
        return json;
    }

    public Response.Status getStatus() {
        return status;
    }


}
