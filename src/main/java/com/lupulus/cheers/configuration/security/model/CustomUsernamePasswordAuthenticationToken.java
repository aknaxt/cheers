package com.lupulus.cheers.configuration.security.model;

import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{
	
private static final long serialVersionUID = 1L;
	

	private Integer manufacturerId;
	
	public CustomUsernamePasswordAuthenticationToken(String username, String password, Set<SimpleGrantedAuthority> authorities, Integer manufacturerId) {
		super(username, password, authorities);
		this.setManufacturerId(manufacturerId);
	}
	
	
	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}


}
