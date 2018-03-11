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

package com.github.jetqin.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author jet
 *
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.github.jetqin")
@EntityScan("com.github.jetqin.domain.*")
@EnableTransactionManagement
@EnableJpaRepositories ("com.github.jetqin.repository")
@EnableAutoConfiguration
public class StartupApplication
{

  private static final Logger log = LoggerFactory.getLogger (StartupApplication.class);


  public static void main (String[] args)
  {
	log.info("Initialize application");
    SpringApplication app = new SpringApplication(StartupApplication.class); 
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

}
