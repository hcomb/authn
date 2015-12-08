package eu.hcomb.authn.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import eu.hcomb.authn.dto.UserDTO;
import eu.hcomb.authn.service.UserService;

@Path("/users")
@Api(tags="user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	protected UserService userService;
	
    @GET
    @Timed
    @ApiOperation(value="User list.", notes = "Get the users list.")
    public List<UserDTO> list() {
    	return userService.getAllUsers();
    }

    @GET
    @Timed
    @Path("/{id}")
    @ApiOperation(value="User by id.", notes = "Get a single user by id.")
    public UserDTO get(@PathParam("id") Long id) {
    	return userService.getUserById(id);
    }

    @POST
    @Timed
    @ApiOperation(value="Add User.", notes = "Add a new user.")
    public UserDTO add(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	return userService.insertUser(user);
    }
    
    @PUT
    @Timed
    @Path("/{id}")
    @ApiOperation(value="Update User", notes = "Update an user by id.")
    public UserDTO update(@PathParam("id") Long id, UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	user.setId(id);
    	return userService.updateUser(user);
    }
    
    @DELETE
    @Timed
    @Path("/{id}")
    @ApiOperation(value="Delete User", notes = "Delete an user by id.")
    public void delete(@PathParam("id") Long id) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	userService.deleteUser(id);
    }

}
