package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.IUserFacade;
import security.UserFacadeFactory;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @GET
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsers()
  {
      IUserFacade uf = UserFacadeFactory.getInstance();
      
      List<User> list = uf.getUsers();
      return Response.status(Response.Status.OK).entity(gson.toJson(list)).build();
      
  }
  
  @DELETE
  @Path("/user")
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteUser(String user)
  {
      IUserFacade uf = UserFacadeFactory.getInstance();
      User u = gson.fromJson(user, User.class);
      uf.deleteUser(u.getUserName());
      return Response.ok().build();
  }
 
}
