package eu.hcomb.authn.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;

import eu.hcomb.authn.dto.UserDTO;
import eu.hcomb.authn.service.UserService;
import eu.hcomb.authn.service.mapper.UserMapper;

public class UserServiceImpl implements UserService {

    @Inject
    private UserMapper userMapper;

	@Transactional
	public UserDTO login(String username, String password) {
		
		UserDTO user = userMapper.getUserByName(username);

		try {
		
			boolean valid = PasswordHash.validatePassword(password, user.getPassword());

			if(valid)
				return user;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	@Transactional
	public List<UserDTO> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	public UserDTO getUserById(Long id) {
		return userMapper.getUserById(id);
	}
	
	public UserDTO insertUser(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = PasswordHash.createHash(user.getPassword());
		user.setPassword(password);
		userMapper.insertUser(user);
		return userMapper.getUserById(user.getId());
	}
	
	public UserDTO updateUser(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = PasswordHash.createHash(user.getPassword());
		user.setPassword(password);
		userMapper.updateUser(user);
		return userMapper.getUserById(user.getId());
	}
	
	public void deleteUser(Long id) {
		userMapper.deleteUser(id);
	}
}
