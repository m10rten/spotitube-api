package nl.han.oose.vdlei.spotitube.domain.login;

import nl.han.oose.vdlei.spotitube.domain.login.presentation.LoginRequest;
import nl.han.oose.vdlei.spotitube.domain.login.presentation.LoginResponse;
import nl.han.oose.vdlei.spotitube.domain.login.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/login")
public class Login {
  @Inject
  LoginService loginService;

  @Path("/")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response loginUser(LoginRequest params) {
    LoginResponse loginResponse = loginService.loginUser(params.getUser(), params.getPassword());
    return Response.status(Response.Status.OK).entity(loginResponse).build();
  }
}
