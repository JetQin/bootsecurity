/** 
 * Project Name:jpa 
 * File Name:CustomerRepository.java 
 * Package Name:com.github.jetqin.jpa
 * Date:Mar 15, 20165:21:56 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.jetqin.domain.Customer;

/** 
 * ClassName: CustomerRepository  
 * 
 * @author jet 
 * @version Configuration Framework 1.0
 * @since JDK 1.7 
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);
  
}
