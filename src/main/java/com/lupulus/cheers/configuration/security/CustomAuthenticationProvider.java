package com.lupulus.cheers.configuration.security;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.configuration.security.model.CustomUsernamePasswordAuthenticationToken;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		Set<SimpleGrantedAuthority> rolesAdmin =  Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		Set<SimpleGrantedAuthority> rolesManufacturer =  Collections.singleton(new SimpleGrantedAuthority("MANUFACTURER"));

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		
		if("admin".equals(name) && "admin".equals(password))
		{
			//return new UsernamePasswordAuthenticationToken(name, password, rolesAdmin);
			return new CustomUsernamePasswordAuthenticationToken(name, password, rolesAdmin, getMockDAOManufacturer(name));
			
		}
		else if(
				"heineken".equals(name) && "heineken".equals(password) ||
				"damm".equals(name) && "damm".equals(password) ||
				"guinness".equals(name) && "guinness".equals(password) ||
				"paulaner".equals(name) && "paulaner".equals(password) ||
				"brewdog".equals(name) && "brewdog".equals(password)
				)
		{
			return new CustomUsernamePasswordAuthenticationToken(name, password, rolesManufacturer, getMockDAOManufacturer(name));
			
		}
		else
			return null;
		
	}
	
	private Integer getMockDAOManufacturer(String name)
	{
		if("heineken".equals(name) )
			return 1;
		else if("damm".equals(name))
			return 2;
		else if("guinness".equals(name))
			return 3;
		else if("paulaner".equals(name))
			return 4;
		else if("brewdog".equals(name))
			return 5;
		else
			return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

