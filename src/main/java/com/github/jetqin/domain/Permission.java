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
// date   : 2016年3月14日
// 
////////////////////////////////////////////////////////////////////////////////

package com.github.jetqin.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author jet
 *
 */
@Data
@Entity
@Table (name = "PERMISSION")
public  class Permission implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = 3770484966056583532L;

  @Id
  @Column (name = "PERMISSION_ID")
  private String permissionId;

  @Column (name = "PERMISSION_NAME")
  private String permissionName;

  @Column (name = "PERMISSION_DESCRIPTION")
  private String description;

  @ManyToMany (mappedBy = "permissions")
  private Set<Role> roles;

  public Permission ()
  {
  }

  public Permission (String permissionId, String permissionName)
  {
    this.permissionId = permissionId;
    this.permissionName = permissionName;
  }

  public Permission (String permissionId, String permissionName, String description)
  {
    this.permissionId = permissionId;
    this.permissionName = permissionName;
    this.description = description;
  }

  @Override
  public String toString ()
  {
    return String.format ("Permission[id=%s,name=%s,description=%s]", permissionId, permissionName, description);
  }

}
