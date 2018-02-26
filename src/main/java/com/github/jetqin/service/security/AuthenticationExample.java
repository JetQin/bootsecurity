package com.github.jetqin.service.security;
//////////////////////////////////////////////////////////////////////////////////
//// 
//// Copyright (C) 2001-2016 the original author or authors.
////
//// Licensed to the Apache Software Foundation (ASF) under one or more
//// contributor license agreements.  See the NOTICE file distributed with
//// this work for additional information regarding copyright ownership.
//// The ASF licenses this file to You under the Apache License, Version 2.0
//// (the "License"); you may not use this file except in compliance with
//// the License.  You may obtain a copy of the License at
////
////      http://www.apache.org/licenses/LICENSE-2.0
////
//// Unless required by applicable law or agreed to in writing, software
//// distributed under the License is distributed on an "AS IS" BASIS,
//// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//// See the License for the specific language governing permissions and
//// limitations under the License.
////
//// date   : 2016年3月8日
//// 
//////////////////////////////////////////////////////////////////////////////////
//package com.github.jetqin.security;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * @author jet
// *
// */
//public class AuthenticationExample
//{
//  private static AuthenticationManager am = new SampleAuthenticationManager();
//  
//  private static AccessDecisionManager adm = new SampleAccessDecisionManager();
//
//  public static void main ( String[] args ) throws Exception
//  {
//    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//    while (true)
//    {
//      System.out.println("Please enter your username:");
//      String name = in.readLine();
//      System.out.println("Please enter your password:");
//      String password = in.readLine();
//      try
//      {
//        Authentication request = new UsernamePasswordAuthenticationToken(name, password);
//        Authentication result = am.authenticate(request);
//        SecurityContextHolder.getContext().setAuthentication(result);
//        break;
//      }
//      catch (AuthenticationException e)
//      {
//        System.out.println("Authentication failed: " + e.getMessage());
//      }
//    }
//    System.out.println("Successfully authenticated. Security context contains: "
//        + SecurityContextHolder.getContext().getAuthentication());
//  }
//
// }
