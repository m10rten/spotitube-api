package nl.han.oose.vdlei.spotitube;

import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;
import nl.han.oose.vdlei.spotitube.utils.json.JSONBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

//@Path("/")
public class App {
  public static void main(String[] args) {
    Person parent = new Person();
    parent.setAge(28);
    parent.setName("parent");

    Person child = new Person();
    child.setAge(2);
    child.setName("child");
    Person child2 = new Person();
    child2.setAge(3);
    child2.setName("child2");
    parent.addChild(child);
    parent.addChild(child2);

    System.out.println(parent.toJson());
    var hash = new HashMethodes();

    var hashed = hash.hash("testhashstring");
    System.out.println(hashed);

  }

//  @GET
//  public Response index(){
//    return Response.status(Response.Status.OK).entity("Up and Running: Tomee Spotitube API.").build();
//  }
}
