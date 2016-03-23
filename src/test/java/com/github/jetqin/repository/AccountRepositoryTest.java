/** 
 * Project Name:bootsecurity 
 * File Name:CustomerRepositoryTest.java 
 * Package Name:com.github.jetqin.repository
 * Date:Mar 16, 20167:14:10 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */

package com.github.jetqin.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jetqin.AbstractTest;
import com.github.jetqin.domain.Account;
import com.github.jetqin.domain.Role;

/**
 * ClassName: CustomerRepositoryTest
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
public class AccountRepositoryTest extends AbstractTest
{

  @Autowired
  AccountRepository accountRepository;

  final Account account = new Account ();

  @Before
  public void setup ()
  {
    account.setAccountId (UUID.randomUUID ().toString ());
    account.setAccountName ("JetQin");
    account.setAccountPassword ("123456");
    account.setDescription ("Admin User");

    final Role role = new Role (String.valueOf (UUID.randomUUID ()), "ADMIN_ROLE", "Admin");
    final Set<Role> roles = new HashSet<Role> ();
    roles.add (role);
    account.setRoles (roles);

  }

  @Test
  public void testCascadeSaveAccount ()
  {
    accountRepository.save (account);
  }

  @Test
  public void save ()
  {
    accountRepository.save (new Account (UUID.randomUUID ().toString (), "Jack", "Bauer"));
    accountRepository.save (new Account (UUID.randomUUID ().toString (), "Chloe", "O'Brian"));
    accountRepository.save (new Account (UUID.randomUUID ().toString (), "Kim", "Bauer"));
    accountRepository.save (new Account (UUID.randomUUID ().toString (), "David", "Palmer"));
    accountRepository.save (new Account (UUID.randomUUID ().toString (), "Michelle", "Dessler"));

    for (Account account : accountRepository.findAll ())
    {
      System.out.println (account.toString ());
    }
  }

}
