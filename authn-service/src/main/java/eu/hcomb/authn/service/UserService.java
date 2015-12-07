package eu.hcomb.authn.service;

import eu.hcomb.authn.dto.UserDTO;

public interface UserService {

	public abstract UserDTO login(String username, String password);

}
