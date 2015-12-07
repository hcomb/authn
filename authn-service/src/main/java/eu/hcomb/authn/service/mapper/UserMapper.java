package eu.hcomb.authn.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import eu.hcomb.authn.dto.UserDTO;

public interface UserMapper {

	@Select("SELECT * FROM users WHERE username = #{username}")
    UserDTO getUserByName(@Param("username") String username);

}