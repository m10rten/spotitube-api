package nl.han.oose.vdlei.spotitube.domain.login.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.service.LoginServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
  @Inject
  LoginServiceImpl loginService;

  @Path("/")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response loginUser(LoginRequest params) {
    LoginResponse loginResponse = loginService.loginUser(params.getUser(), params.getPassword());
    return Response.status(loginResponse.getStatus()).entity(loginResponse.getJson()).build();
  }
}
