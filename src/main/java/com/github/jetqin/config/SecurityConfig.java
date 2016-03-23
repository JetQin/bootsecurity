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
// date   : 2016年3月9日
// 
////////////////////////////////////////////////////////////////////////////////

package com.github.jetqin.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jet
 *
 */

@Configuration
@Order (SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

  @Override
  protected void configure (HttpSecurity http) throws Exception
  {
    http.authorizeRequests ().anyRequest ().fullyAuthenticated ().and ().formLogin ().loginPage ("/login").failureUrl (
        "/login?error").permitAll ().and ().logout ().permitAll ();
  }

  @Override
  public void configure (AuthenticationManagerBuilder auth) throws Exception
  {
    auth.inMemoryAuthentication ().withUser ("admin").password ("admin").roles ("ADMIN", "USER").and ().withUser (
        "user").password ("user").roles ("USER");
  }
}
