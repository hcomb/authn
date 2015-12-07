package eu.hcomb.authn.service.impl;

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
}
