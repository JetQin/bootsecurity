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
// date   : 2016年3月16日
// 
////////////////////////////////////////////////////////////////////////////////
package com.github.jetqin.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author jet
 *
 */

@Configuration
public class DatabaseConfig
{

  private static final String PROPERTY_NAME_DATABASE_DRIVER                = "spring.datasource.driverClassName";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD              = "spring.datasource.password";
  private static final String PROPERTY_NAME_DATABASE_URL                   = "spring.datasource.url";
  private static final String PROPERTY_NAME_DATABASE_USERNAME              = "spring.datasource.username";
  private static final String PROPERTY_NAME_HIBERNATE_DIALECT              = "hibernate.dialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL             = "hibernate.show_sql";
  private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO         = "hibernate.hbm2ddl.auto";
  private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

  @Resource
  private Environment         env;

  @Bean
  public DataSource dataSource ( )
  {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
//    dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
//    dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
//    dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    
//    Properties props = new Properties();
//    props.setProperty("dataSourceClassName", env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
//    props.setProperty("dataSource.user", env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
//    props.setProperty("dataSource.password", env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
//    props.setProperty("dataSource.jdbcUrl", env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));

    HikariConfig config = new HikariConfig();
    config.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    config.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    config.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    config.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    
    HikariDataSource dataSource = new HikariDataSource(config);
    return dataSource;
  }

//  @Bean
//  public DataSource dataSource() {
//
//    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//    return builder.setType(EmbeddedDatabaseType.H2).build();
//  }

  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory ( )
  {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
    entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
    entityManagerFactoryBean.setJpaProperties(hibProperties());
    entityManagerFactoryBean.afterPropertiesSet();
    entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return entityManagerFactoryBean;
  }

  private Properties hibProperties ( )
  {
    Properties properties = new Properties();
    properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
    properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
    properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, env.getProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
    return properties;
  }

}
