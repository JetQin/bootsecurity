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
import com.github.jetqin.service.CustomerService;

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
  CustomerService customerService;

  @Test
  public void saveCustomer ( )
  {
    customerService.saveCustomer(new Customer("Jack", "Bauer"));
    customerService.saveCustomer(new Customer("Chloe", "O'Brian"));
    customerService.saveCustomer(new Customer("Kim", "Bauer"));
    customerService.saveCustomer(new Customer("David", "Palmer"));
    customerService.saveCustomer(new Customer("Michelle", "Dessler"));
  }

  @Test
  public void findAll ( )
  {
    for (Customer customer : customerService.findAll())
    {
      System.out.println(customer.toString());
    }
  }

}
