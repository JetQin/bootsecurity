package com.github.jetqin.service.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.jetqin.domain.User;


@Component
public class CustomUserDetails extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4973229132091330836L;
	
	private List<String> userRoles;
	
	public CustomUserDetails() {
	}
	
	public CustomUserDetails(User user) {
		super(user);
	}
	
	public CustomUserDetails(User user, List<String> userRoles) {
		super(user);
		this.userRoles = userRoles;		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}
}
