package eu.hcomb.authn.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import eu.hcomb.authn.dto.UserDTO;

public interface UserService {

	public abstract UserDTO login(String username, String password);

	public abstract List<UserDTO> getAllUsers();

	public abstract UserDTO getUserById(Long id);

	public abstract UserDTO insertUser(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException;

	public abstract UserDTO updateUser(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException;

	public abstract void deleteUser(Long id);
}
