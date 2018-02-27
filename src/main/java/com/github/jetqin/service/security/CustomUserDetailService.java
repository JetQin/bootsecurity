package com.github.jetqin.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.jetqin.domain.Role;
import com.github.jetqin.domain.User;
import com.github.jetqin.repository.UserRepository;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if(user.equals(null)) {
			throw new UsernameNotFoundException("No user present with username:".concat(username));
		}else {
			List<String> userRoles = fetchRoleName(user);
			CustomUserDetails userDetail = new CustomUserDetails(user,userRoles);
			return userDetail;
		}
		
	}
	
	private List<String> fetchRoleName(User user){
		List<String> roleNameList = new ArrayList<String>();
		for(Role role : user.getRoles()) {
			roleNameList.add(role.getRoleName());
		}
		return roleNameList;
	}

}
