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
import com.github.jetqin.domain.Account;

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

  @Test
  public void save ( )
  {
    accountRepository.save(new Account(UUID.randomUUID().toString(),"Jack", "Bauer"));
    accountRepository.save(new Account(UUID.randomUUID().toString(),"Chloe", "O'Brian"));
    accountRepository.save(new Account(UUID.randomUUID().toString(),"Kim", "Bauer"));
    accountRepository.save(new Account(UUID.randomUUID().toString(),"David", "Palmer"));
    accountRepository.save(new Account(UUID.randomUUID().toString(),"Michelle", "Dessler"));
    
    for (Account account : accountRepository.findAll())
    {
      System.out.println(account.toString());
    }
  }


}
