package nl.han.oose.vdlei.spotitube.domain.user.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.service.LoginServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
  private LoginServiceImpl loginService;

  @Inject
  public void setLoginService(LoginServiceImpl loginService) {
    this.loginService = loginService;
  }

  @Path("/")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response loginUser(LoginRequest params) {
    try {
      LoginResponse response = loginService.loginUser(params.getUser(), params.getPassword());
      return Response.status(Response.Status.OK).entity(response).build();
    } catch (NotAuthorizedException e) {
      return Response.status(Response.Status.FORBIDDEN).entity("Invalid combination: User + Password").build();
    }
  }
}
