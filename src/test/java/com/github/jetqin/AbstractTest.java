/** 
 * Project Name:bootsecurity 
 * File Name:AbstractTest.java 
 * Package Name:com.github.jetqin
 * Date:Mar 16, 20167:14:28 PM 
 * Copyright (c) 2016, jianlei.qin@sktlab.com All Rights Reserved. 
 * 
 */

package com.github.jetqin;

import com.github.jetqin.startup.StartupApplication;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * ClassName: AbstractTest
 * 
 * @author jet
 * @version Configuration Framework 1.0
 * @since JDK 1.8
 */

@RunWith (SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { MockServletContext.class, StartupApplication.class })
@WebAppConfiguration
public class AbstractTest
{

}
