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
 * @author jet
 *
 */
@Data
@Entity
@Table (name = "ACCOUNT")
public class Account implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = 3366461526182564088L;

  @Id
  @Column (name = "ACCOUNT_ID")
  private String accountId;

  @Column (name = "ACCOUNT_NAME")
  private String accountName;

  @Column (name = "ACCOUNT_PASSWORD")
  private String accountPassword;

  @Column (name = "DESCRIPTION")
  private String description;

  @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable (
      name = "ACCOUNT_ROLE", joinColumns = { @JoinColumn (name = "ACCOUNT_ID") }, inverseJoinColumns = { @JoinColumn (
          name = "ROLE_ID") })
  private Set<Role> roles;

  public Account ()
  {
  }

  public Account (String id, String name)
  {
    this.accountId = id;
    this.accountName = name;
  }

  public Account (String id, String name, String description)
  {
    this.accountId = id;
    this.accountName = name;
    this.description = description;
  }

  @Override
  public String toString ()
  {
    return String.format ("Account[id=%s, name='%s', description='%s']", accountId, accountName, description);
  }

}
