/** 
 * Project Name:bootsecurity 
 * File Name:Role.java 
 * Package Name:com.github.jetqin.domain
 * Date:Mar 14, 20165:08:55 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * ClassName: Role
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable
{
  @Id
  @Column(name = "ROLE_ID")
  private String          roleId;

  @Column(name = "ROLE_NAME")
  private String          roleName;

  @Column(name = "DESCRIPTION")
  private String          description;

  @ManyToMany
  @JoinTable(name = "ACCOUNT_ROLE", joinColumns = { @JoinColumn(name = "accountId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
  private Set<Account>    accounts;

  @ManyToMany
  @JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "permissionId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
  private Set<Permission> permissions;
}
