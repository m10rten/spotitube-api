package resources;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class UserLogin {
@Inject
UserService userService;
    @Path("/")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginResourse params){
        LoginResponse loginResponse = userService.loginUser(params.user, params.password);
        return Response.status(Response.Status.OK).entity(loginResponse).build();
    }
}
