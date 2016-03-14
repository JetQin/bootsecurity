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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.jetqin.domain.Account;
import com.github.jetqin.repository.AccountRepository;

/**
 * @author jet
 *
 */
@Configuration
@ComponentScan
@SpringBootApplication
public class StartupApplication
{
  
  private Logger log = LoggerFactory.getLogger(StartupApplication.class);

  
  public static void main ( String[] args )
  {
    SpringApplication.run(StartupApplication.class, args);
  }
  
  @Bean
  public CommandLineRunner demo(AccountRepository repository) {
      return (args) -> {
          // save a couple of customers
          repository.save(new Account("Jack", "Bauer"));
          repository.save(new Account("Chloe", "O'Brian"));
          repository.save(new Account("Kim", "Bauer"));
          repository.save(new Account("David", "Palmer"));
          repository.save(new Account("Michelle", "Dessler"));

          // fetch all customers
          log.info("Customers found with findAll():");
          log.info("-------------------------------");
          for (Account customer : repository.findAll()) {
              log.info(customer.toString());
          }
          log.info("");

          // fetch an individual customer by ID
          Account account = repository.findOne("Chloe");
          log.info("Customer found with findOne(Chloe):");
          log.info("--------------------------------");
          log.info(account.toString());
          log.info("");

      };
  }
}
