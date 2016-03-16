/** 
 * Project Name:bootsecurity 
 * File Name:CustomerRepositoryTest.java 
 * Package Name:com.github.jetqin.repository
 * Date:Mar 16, 20167:14:10 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jetqin.AbstractTest;
import com.github.jetqin.domain.Customer;

/**
 * ClassName: CustomerRepositoryTest
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */
public class CustomerRepositoryTest extends AbstractTest
{

  @Autowired
  CustomerRepository customerRepository;

  @Test
  public void save ( )
  {
    customerRepository.save(new Customer("Jack", "Bauer"));
    customerRepository.save(new Customer("Chloe", "O'Brian"));
    customerRepository.save(new Customer("Kim", "Bauer"));
    customerRepository.save(new Customer("David", "Palmer"));
    customerRepository.save(new Customer("Michelle", "Dessler"));
    
    for (Customer customer : customerRepository.findAll())
    {
      System.out.println(customer.toString());
    }
  }


}
