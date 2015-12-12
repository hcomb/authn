package eu.hcomb.authn.client;



import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;

import eu.hcomb.authn.dto.PrincipalDTO;
import eu.hcomb.authn.dto.TokenDTO;

public class TestAuthnClient {

	public static void main(String[] args) {
		Client jerseyClient = new JerseyClientBuilder().build();
		String targetUrl = "http://localhost:8280/authn/api";
		
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
	
		}

	}
}
