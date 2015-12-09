package eu.hcomb.authn.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import eu.hcomb.authn.dto.PrincipalDTO;
import eu.hcomb.authn.dto.TokenDTO;
import eu.hcomb.authn.dto.UserDTO;

@Singleton
public class AuthenticationClient {

	@Inject
	private Client jerseyClient;
	
	@Inject
	@Named("authn.url")
	private String targetUrl;
	
    public void setJerseyClient(Client jerseyClient) {
		this.jerseyClient = jerseyClient;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int deleteUser(TokenDTO token, Long id) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/users/"+id);

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).delete();
        
        return response.getStatus();
	}

	public UserDTO updateUser(TokenDTO token, Long id, UserDTO user) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/users/"+id);

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).put(Entity.entity(user, MediaType.APPLICATION_JSON));
        
        return response.readEntity(UserDTO.class);
	}
	
	public UserDTO insertUser(TokenDTO token, UserDTO user) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/users");

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).post(Entity.entity(user, MediaType.APPLICATION_JSON));
        
        return response.readEntity(UserDTO.class);
	}
	
	public UserDTO getUserById(TokenDTO token, Long id) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/users/"+id);

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).get();
        
        return response.readEntity(UserDTO.class);
	}
	
	public List<UserDTO> getAllUsers(TokenDTO token) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/users");

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).get();
        
        return response.readEntity(new GenericType<List<UserDTO>>(){});
	}

	public PrincipalDTO whoami(TokenDTO token) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/whoami");

        Invocation.Builder invocationBuilder = webResource.request();
        
        Response response = invocationBuilder.header("Authorization", "Bearer "+token.getValue()).get();
        
        return response.readEntity(PrincipalDTO.class);
	}
	
	public TokenDTO login(String username, String password) {
        WebTarget webResource = jerseyClient.target(targetUrl).path("/login");

        Invocation.Builder invocationBuilder = webResource.request();
        
        Form form = new Form(); 
        form.param("username", username); 
        form.param("password", password); 
        
        Response response = invocationBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

        return response.readEntity(TokenDTO.class);
    }
}
