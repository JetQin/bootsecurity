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
import com.github.jetqin.domain.Role;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

  final Role role = new Role ();

  @Before
  public void setup ()
  {
    role.setRoleId (String.valueOf (UUID.randomUUID ()));
    role.setRoleName ("ROLE_ADMIN");
    role.setDescription ("ROLE Admin");

    final Set<Permission> permissions = new HashSet<Permission> ();
    final Permission permission = new Permission (String.valueOf (UUID.randomUUID ()), "READ_ADMIN", "READ_ADMIN");
    permissions.add (permission);
    role.setPermissions (permissions);
  }

  @Test
  public void testSaveRolePermission ()
  {
    roleRepository.save (role);
  }

  @Test
  public void save ()
  {
    roleRepository.save (new Role (UUID.randomUUID ().toString (), "Admin", "Admin"));
    roleRepository.save (new Role (UUID.randomUUID ().toString (), "User", "User"));
    roleRepository.save (new Role (UUID.randomUUID ().toString (), "Travelor", "Travelor"));

    for (Role role : roleRepository.findAll ())
    {
      System.out.println (role.toString ());
    }
  }

}
