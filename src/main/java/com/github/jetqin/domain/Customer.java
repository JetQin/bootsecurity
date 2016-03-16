/** 
 * Project Name:jpa 
 * File Name:Customer.java 
 * Package Name:com.github.jetqin.jpa
 * Date:Mar 15, 20165:21:07 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */
package com.github.jetqin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * ClassName: Customer  
 * 
 * @author jet 
 * @version Configuration Framework 1.0
 * @since JDK 1.7 
 */

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="LAST_NAME")
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