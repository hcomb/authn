package eu.hcomb.authn.dto;

import java.util.List;

public class PrincipalDTO {

	protected String name;
	protected List<String> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
