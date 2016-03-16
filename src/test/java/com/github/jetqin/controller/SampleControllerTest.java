////////////////////////////////////////////////////////////////////////////////
// 
// Copyright (C) 2001-2016 the original author or authors.
//
// Licensed to the Apache Software Foundation (ASF) under one or more
// contributor license agreements.  See the NOTICE file distributed with
// this work for additional information regarding copyright ownership.
// The ASF licenses this file to You under the Apache License, Version 2.0
// (the "License"); you may not use this file except in compliance with
// the License.  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// date   : 2016年3月10日
// 
////////////////////////////////////////////////////////////////////////////////
package com.github.jetqin.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.jetqin.AbstractTest;

/**
 * @author jet
 *
 */
public class SampleControllerTest extends AbstractTest
{
  private MockMvc mvc;

  @Before
  public void setUp ( ) throws Exception
  {
    mvc = MockMvcBuilders.standaloneSetup(new SampleController()).build();
  }

  @Test
  public void getHello ( ) throws Exception
  {
    mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().string(equalTo("Hello World!")));
  }
}
