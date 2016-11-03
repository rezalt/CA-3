package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.PasswordStorage;

@Path("demouser")
public class RESTUser {
  
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  UserFacade uf = new UserFacade();    
    
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"REST call accesible by only authenticated USERS\"}"; 
  }
  
  @POST
  @Path("/add")
  public void newUser(String user) throws PasswordStorage.CannotPerformOperationException{
      JsonObject addUser = new JsonParser().parse(user).getAsJsonObject();
      entity.User u = new entity.User();
      u.setUserName(addUser.get("username").getAsString());
      u.setPassword(PasswordStorage.createHash(addUser.get("password").getAsString()));
      
      uf.addUser(u);
  }
 
}