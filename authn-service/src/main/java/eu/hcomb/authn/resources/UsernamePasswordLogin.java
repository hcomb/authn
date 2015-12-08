package eu.hcomb.authn.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import eu.hcomb.authn.dto.UserDTO;
import eu.hcomb.authn.service.UserService;
import eu.hcomb.authz.client.AuthorizationClient;
import eu.hcomb.common.dto.Token;
import eu.hcomb.common.service.TokenService;

@Api
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class UsernamePasswordLogin {

	@Inject
	protected TokenService tokenService;
	
	@Inject
	protected UserService userService;
	
    @Inject
    private AuthorizationClient authzClient;
	
    @POST
    @Timed
    @ApiOperation(value="User login.", notes = "Let an user login, given an username and password. In case of successful login returns a JWT token.")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Token login(
    		@ApiParam(required = true) @FormParam("username") String username, 
    		@ApiParam(required = true) @FormParam("password") String password
    	) {

    	UserDTO user = userService.login(username, password);
    	
    	if(user == null)
    		return new Token(false);
    	
    	List<String> roles = authzClient.getRolesByUser(user.getUsername());
    	
    	Token token = tokenService.getToken(user.getUsername(), roles);
    	
    	return token;
    }

}
