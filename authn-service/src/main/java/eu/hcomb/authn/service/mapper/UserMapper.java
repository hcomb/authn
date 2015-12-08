package eu.hcomb.authn.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import eu.hcomb.authn.dto.UserDTO;

public interface UserMapper {

	@Select("SELECT * FROM users WHERE username = #{username}")
    UserDTO getUserByName(@Param("username") String username);

	@Select("SELECT id, username FROM users")
	List<UserDTO> getAllUsers();

	@Select("SELECT id, username FROM users WHERE id = #{id}")
	UserDTO getUserById(@Param("id") Long id);

	@Insert("INSERT INTO users (username,password) VALUES (#{username}, #{password})")
	@Options(useGeneratedKeys=true)
	Long insertUser(UserDTO user);

	@Update("UPDATE users SET username = #{username}, password = #{password} WHERE id = #{id}")
	void updateUser(UserDTO user);

	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteUser(@Param("id") Long id);
}