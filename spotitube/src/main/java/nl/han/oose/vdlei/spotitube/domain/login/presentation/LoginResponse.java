package nl.han.oose.vdlei.spotitube.domain.login.presentation;

import javax.ws.rs.core.Response;

public class LoginResponse {
  private String json;
  private Response.Status status = Response.Status.UNAUTHORIZED;

  public void setJson(String json) {
    this.json = json;
  }

  public String getJson() {
    return json;
  }

  public Response.Status getStatus() {
    return this.status;
  }

  public void setStatus(Response.Status status) {
    this.status = status;
  }
}
