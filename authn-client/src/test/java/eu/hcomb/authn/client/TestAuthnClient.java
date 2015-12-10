package eu.hcomb.authn.client;



import java.security.SecureRandom;
import java.util.List;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;

import eu.hcomb.authn.dto.PrincipalDTO;
import eu.hcomb.authn.dto.TokenDTO;
import eu.hcomb.authn.dto.UserDTO;

public class TestAuthnClient {

	public static void main(String[] args) {
		Client jerseyClient = new JerseyClientBuilder().build();
		String targetUrl = "http://localhost:8080/authn/api";
		
		AuthenticationClient client = new AuthenticationClient();
		client.setJerseyClient(jerseyClient);
		client.setTargetUrl(targetUrl);
		
		TokenDTO token = client.login("alex", "pippo");

		System.out.println(token.getValue());

		System.out.println("1 =====");

		for (int i = 0; i < 1000; i++) {
			
			PrincipalDTO whoami = client.whoami(token);
			System.out.println(whoami.getName());
			System.out.println(whoami.getRoles());
	
			System.out.println("2 =====");
			
			List<UserDTO> users = client.getAllUsers(token);
			System.out.println(users);
	
			System.out.println("3 =====");
			for (UserDTO dto : users) {
				UserDTO check = client.getUserById(token, dto.getId());
				if(check!=null)
					System.out.println(check.getUsername());
			}
			System.out.println("4 =====");
			UserDTO newUser = new UserDTO();
			newUser.setUsername(""+new SecureRandom().nextDouble());
			newUser.setPassword("test345");
			UserDTO check = client.insertUser(token, newUser);
			System.out.println(check.getId() + " - " + check.getUsername());
			
			System.out.println("5 =====");
			newUser.setUsername(""+new SecureRandom().nextDouble());
			newUser.setPassword("bbbbbbb");
			check = client.updateUser(token, check.getId(), newUser);
			System.out.println(check.getId() + " - " + check.getUsername());
			
			System.out.println("6 =====");
	
			int status = client.deleteUser(token, check.getId());
			System.out.println(status);

		}

	}
}
