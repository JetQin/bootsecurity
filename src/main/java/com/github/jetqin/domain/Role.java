/** 
 * Project Name:bootsecurity 
 * File Name:Role.java 
 * Package Name:com.github.jetqin.domain
 * Date:Mar 14, 20165:08:55 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */

package com.github.jetqin.domain;

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
 * ClassName: Role
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
@Data
@Entity
@Table (name = "ROLE")
public class Role implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = -2680375922723920512L;

  @Id
  @Column (name = "ROLE_ID")
  private String roleId;

  @Column (name = "ROLE_NAME")
  private String roleName;

  @Column (name = "DESCRIPTION")
  private String description;

  @ManyToMany (mappedBy = "roles")
  private Set<Account> accounts;

  @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable (
      name = "PERMISSION_ROLE", joinColumns = { @JoinColumn (name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn (
          name = "PERMISSION_ID") })
  private Set<Permission> permissions;

  public Role ()
  {
  }

  public Role (String roleId, String roleName)
  {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  /**
   * 
   * @param roleId       role id is a uuid
   * @param roleName     role name
   * @param description  role description
   */
  public Role (String roleId, String roleName, String description)
  {
    this.roleId = roleId;
    this.roleName = roleName;
    this.description = description;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString ()
  {
    // TODO Auto-generated method stub
    return String.format ("Role[id=%s,name=%s,description=%s]", roleId, roleName, description);
  }
}
