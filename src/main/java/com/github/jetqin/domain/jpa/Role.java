/** 
 * Project Name:bootsecurity 
 * File Name:Role.java 
 * Package Name:com.github.jetqin.domain
 * Date:Mar 14, 20165:08:55 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */

package com.github.jetqin.domain.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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

  @Column (name = "ROLE_DESCRIPTION")
  private String description;

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
    return String.format ("Role[id=%s,name=%s,description=%s]", roleId, roleName, description);
  }
}
