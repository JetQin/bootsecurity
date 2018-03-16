////////////////////////////////////////////////////////////////////////////////
// 
// Copyright (C) 2001-2016 the original author or authors.
//
// Licensed to the Apache Software Foundation (ASF) under one or more
// contributor license agreements.  See the NOTICE file distributed with
// this work for additional information regarding copyright ownership.
// The ASF licenses this file to You under the Apache License, Version 2.0
// (the "License"); you may not use this file except in compliance with
// the License.  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// date   : 2016年3月10日
// 
////////////////////////////////////////////////////////////////////////////////

package com.github.jetqin.domain.jpa;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author jet
 *
 */
@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3366461526182564088L;

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACTIVATED")
	private boolean activated;

	@Column(name = "ACTIVATIONKEY")
	private String activationKey;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "TELEPHONE")
	private String telephone;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACCOUNT_EXPIRED")
	private boolean accountNonExpired;
	
	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountNonLocked;
	
	@Column(name = "CREDENTIAL_EXPIRED")
	private boolean credentialsNonExpired;
	
	@Column(name = "ENABLED")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
		    joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;

	public User() {
	}

	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}

	public User(String id,String name, String password) {
		this.userId = id;
		this.username = name;
		this.password = password;
	}
	
	public User(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.activated = user.isActivated();
		this.activationKey = user.getActivationKey();
		this.email = user.getEmail();
		this.telephone = user.getTelephone();
		this.description = user.getDescription();
		this.accountNonExpired = user.isAccountNonExpired();
		this.accountNonLocked = user.isAccountNonLocked();
		this.credentialsNonExpired = user.isCredentialsNonExpired();
		this.enabled = user.isEnabled();
		this.roles = user.getRoles();
	}

	
	@Override
	public String toString() {
		return String.format("Account[id=%s, name='%s', description='%s']", userId, username, description);
	}

	public User(String userId, String username, String password, boolean activated, String activationKey, String email,
			String telephone, String description, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled, Set<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.activated = activated;
		this.activationKey = activationKey;
		this.email = email;
		this.telephone = telephone;
		this.description = description;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.roles = roles;
	}

}
