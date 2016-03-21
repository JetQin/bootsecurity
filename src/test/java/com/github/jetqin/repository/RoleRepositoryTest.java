/** 
 * Project Name:bootsecurity 
 * File Name:CustomerRepositoryTest.java 
 * Package Name:com.github.jetqin.repository
 * Date:Mar 16, 20167:14:10 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.repository;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jetqin.AbstractTest;
import com.github.jetqin.domain.Role;

/**
 * ClassName: CustomerRepositoryTest
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
public class RoleRepositoryTest extends AbstractTest
{

  @Autowired
  RoleRepository roleRepository;

  @Test
  public void save ( )
  {
    roleRepository.save(new Role(UUID.randomUUID().toString(),"Admin", "Admin"));
    roleRepository.save(new Role(UUID.randomUUID().toString(),"User",   "User"));
    roleRepository.save(new Role(UUID.randomUUID().toString(),"Travelor", "Travelor"));
    
    for (Role role : roleRepository.findAll())
    {
      System.out.println(role.toString());
    }
  }


}
