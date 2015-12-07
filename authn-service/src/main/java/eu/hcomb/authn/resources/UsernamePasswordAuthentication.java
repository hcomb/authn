package eu.hcomb.authn.resources;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.hcomb.common.dto.Token;
import eu.hcomb.common.service.TokenService;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class UsernamePasswordAuthentication {

	@Inject
	protected TokenService tokenService;
	
    @GET
    @Timed
    public Token login(@QueryParam("username") Optional<String> username, @QueryParam("password") Optional<String> password) {

    	Token token = tokenService.getToken(username.get(), new String[]{"user"});
    	
    	return token;
    }

}
