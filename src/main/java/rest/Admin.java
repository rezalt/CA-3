package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class Admin {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserFacade uf = new UserFacade();
  
  @GET
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsers()
  {
      
      List<User> list = uf.getUsers();
      return Response.status(Response.Status.OK).entity(gson.toJson(list)).build();
      
  }
  
  @PUT
  @Path("/user")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteUser(String user)
  {
      User u = gson.fromJson(user, User.class);
      uf.deleteUser(u.getUserName());
      return Response.status(Response.Status.OK).entity(gson.toJson(u)).build();
  }
 
}
