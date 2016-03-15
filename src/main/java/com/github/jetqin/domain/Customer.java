/** 
 * Project Name:jpa 
 * File Name:Customer.java 
 * Package Name:com.github.jetqin.jpa
 * Date:Mar 15, 20165:21:07 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/** 
 * ClassName: Customer  
 * 
 * @author jet 
 * @version Configuration Framework 1.0
 * @since JDK 1.7 
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}