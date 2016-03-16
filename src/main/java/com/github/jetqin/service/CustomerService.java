/** 
 * Project Name:bootsecurity 
 * File Name:CustomerService.java 
 * Package Name:com.github.jetqin.service
 * Date:Mar 16, 20167:15:46 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jetqin.domain.Customer;
import com.github.jetqin.repository.CustomerRepository;

/**
 * ClassName: CustomerService
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.7
 */

@Service
public class CustomerService
{

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService (CustomerRepository customerRepository)
  {
    this.customerRepository = customerRepository;
  }

  public void saveCustomer (Customer customer)
  {
    customerRepository.save(customer);
  }

  public List<Customer> findAll ( )
  {
    return customerRepository.findAll();
  }

}
