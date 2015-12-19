package eu.hcomb.authn.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import eu.hcomb.authn.LoginEvents;
import eu.hcomb.authz.client.UserCRUDClient;
import eu.hcomb.authz.dto.UserDTO;
import eu.hcomb.common.dto.Token;
import eu.hcomb.common.service.EventEmitter;
import eu.hcomb.common.service.TokenService;

@Api(tags="login")
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class UsernamePasswordLogin {

	@Inject
	protected TokenService tokenService;
	
    @Inject
    protected UserCRUDClient userClient;
	
    @Inject 
    protected EventEmitter eventEmitter;

    @POST
    @Timed
    @ApiOperation(value="User login.", notes = "Let an user login, given an username and password. In case of successful login returns a JWT token.")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Token login(
    		@ApiParam(required = true) @FormParam("username") String username, 
    		@ApiParam(required = true) @FormParam("password") String password
    	) {

    	UserDTO user = userClient.login(username, password);

    	if(user == null){
    		eventEmitter.emit(LoginEvents.FAILED_LOGIN, username);
    		return new Token(false);
    	}
    	
    	eventEmitter.emit(LoginEvents.SUCCESS_LOGIN, user);

    	Token token = tokenService.getToken(user.getUsername(), user.getRoles());
    	
    	return token;
    }

}
