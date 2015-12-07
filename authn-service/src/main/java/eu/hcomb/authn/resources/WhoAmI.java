package eu.hcomb.authn.resources;

import io.dropwizard.auth.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import eu.hcomb.common.dto.User;
import eu.hcomb.common.service.TokenService;

@Path("/whoami")
@Produces(MediaType.APPLICATION_JSON)
public class WhoAmI {

	@Inject
	protected TokenService tokenService;
	
    @GET
    @Timed
    @RolesAllowed("user")
    public User login(@Auth User user) {

    	return user;
    }

}
