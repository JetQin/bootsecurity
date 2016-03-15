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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.jetqin.domain.Customer;
import com.github.jetqin.repository.CustomerRepository;

/**
 * @author jet
 *
 */

@EnableJpaRepositories
@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.github.jetqin"})
//@EnableOrmConfiguration(showSql=false,packageToScan="com.github.jetqin.domain",enableCache=true)
public class StartupApplication
{
  
  private Logger log = LoggerFactory.getLogger(StartupApplication.class);

  
  
  public static void main ( String[] args )
  {
    SpringApplication.run(StartupApplication.class);
  }
  
  @Bean
  public CommandLineRunner demo( CustomerRepository repository) {
      return (args) -> {
          // save a couple of customers
          repository.save(new Customer("Jack", "Bauer"));
          repository.save(new Customer("Chloe", "O'Brian"));
          repository.save(new Customer("Kim", "Bauer"));
          repository.save(new Customer("David", "Palmer"));
          repository.save(new Customer("Michelle", "Dessler"));

          // fetch all customers
          log.info("Customers found with findAll():");
          log.info("-------------------------------");
          for (Customer customer : repository.findAll()) {
              log.info(customer.toString());
          }
          log.info("");

      };
  }
}
