package com.lupulus.cheers.configuration.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.service.CatalogService;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Autowired
	CatalogService catalogService;

	public boolean hasPermission(String id) {
		return id.equals("correct");
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
			return false;
		}
		String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

		return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());
	}


	private boolean hasPrivilege(Authentication authentication, String targetType, String permission) {
		for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
			if (grantedAuth.getAuthority().startsWith(targetType) && grantedAuth.getAuthority().contains(permission)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasAuthority(Authentication authentication, String authority) {
		for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
			if (grantedAuth.getAuthority().contains(authority)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
			return false;
		}
		if (hasAuthority(authentication, "ADMIN"))
			return true;
		else if ("MANUFACTURER".equals(permission)) {
			try {
				// check has privilege
				if (hasAuthority(authentication, "MANUFACTURER")) {	
					if("DeleteBeerRequest".equals(targetType))
					{
						Beer beer = catalogService.getBeer(new Integer(targetId.toString()));
						if (beer!= null && beer.getManufacturer().getId() == ((com.lupulus.cheers.configuration.security.model.CustomUsernamePasswordAuthenticationToken) authentication).getManufacturerId())
							return true;
					}
					else if (targetId.equals(((com.lupulus.cheers.configuration.security.model.CustomUsernamePasswordAuthenticationToken) authentication).getManufacturerId()))
						return true;
				}
				return false;
			} catch (Exception ex) {
				return false;
			}
		} else
			return false;
	}



}
