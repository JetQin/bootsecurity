/** 
 * Project Name:bootsecurity 
 * File Name:CustomerRepositoryTest.java 
 * Package Name:com.github.jetqin.repository
 * Date:Mar 16, 20167:14:10 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */

package com.github.jetqin.repository;

import com.github.jetqin.AbstractTest;
import com.github.jetqin.domain.Permission;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * ClassName: CustomerRepositoryTest
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
public class PermissionRepositoryTest extends AbstractTest
{

  @Autowired
  PermissionRepository permissionRepository;

  @Test
  public void save ()
  {
    permissionRepository.save (new Permission (UUID.randomUUID ().toString (), "Read", "read permission"));
    permissionRepository.save (new Permission (UUID.randomUUID ().toString (), "Write", "write permission"));
    permissionRepository.save (new Permission (UUID.randomUUID ().toString (), "Execute", "execute permission"));

    for (Permission permission : permissionRepository.findAll ())
    {
      System.out.println (permission.toString ());
    }
  }

}
