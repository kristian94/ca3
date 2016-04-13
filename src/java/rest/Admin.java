package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Role;
import entity.User;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {

    private static final UserFacade uf = new UserFacade();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        JsonArray json = new JsonArray();
        List<User> users = uf.getUsers();
        for (User user : users) {
            JsonObject jsonUser = new JsonObject();
            jsonUser.addProperty("username", user.getUserName());
            
            JsonArray jsonRoles = new JsonArray();
            
            for (Role role : user.getRoles()) {
                JsonObject jsonRole = new JsonObject();
                jsonRole.addProperty("rolename", role.getRoleName());
                jsonRoles.add(jsonRole);
            }
            jsonUser.add("roles", jsonRoles);
            json.add(jsonUser);
        }
        return gson.toJson(json);

    }

    @DELETE
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") String id) {
        try {
            User u = uf.deleteUser(id);
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
            //return gson.toJson("Something went wrong");
        }
        return "jear";
        
//        if (u != null) {
//            return gson.toJson("User was deleted");
//        } else {
//            return gson.toJson("Something went wrong");
//        }
    }
}
