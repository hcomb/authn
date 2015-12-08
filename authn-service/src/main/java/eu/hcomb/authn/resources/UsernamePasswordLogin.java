package eu.hcomb.authn.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.hcomb.authn.dto.UserDTO;
import eu.hcomb.authn.service.UserService;
import eu.hcomb.authz.client.AuthorizationClient;
import eu.hcomb.common.dto.Token;
import eu.hcomb.common.service.TokenService;

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
    public Token login(@FormParam("username") Optional<String> username, @FormParam("password") Optional<String> password) {

    	UserDTO user = userService.login(username.get(), password.get());
    	
    	if(user == null)
    		return new Token(false);
    	
    	List<String> roles = authzClient.getRolesByUser(user.getUsername());
    	
    	Token token = tokenService.getToken(user.getUsername(), roles);
    	
    	return token;
    }

}
