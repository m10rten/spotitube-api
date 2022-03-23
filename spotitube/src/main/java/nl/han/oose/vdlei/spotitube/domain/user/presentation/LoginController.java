package nl.han.oose.vdlei.spotitube.domain.user.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.service.LoginServiceImpl;
import nl.han.oose.vdlei.spotitube.domain.user.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
  private LoginService loginService;

  @Inject
  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

  @Path("/")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response loginUserEndpoint(LoginRequest params) {
    try {
      LoginResponse response = loginService.loginUser(params.getUser(), params.getPassword());
      return Response.status(Response.Status.OK).entity(response).build();
    } catch (NotAuthorizedException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid combination: User + Password").build();
    }
  }
}
