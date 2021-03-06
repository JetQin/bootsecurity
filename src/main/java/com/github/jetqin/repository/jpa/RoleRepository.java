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
//// date   : 2016年3月14日
//// 
//////////////////////////////////////////////////////////////////////////////////
//package com.github.jetqin.repository;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import com.github.jetqin.domain.Account;
///**
// * @author jet
// *
// */
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// date   : 2016年3月14日
// 
////////////////////////////////////////////////////////////////////////////////

package com.github.jetqin.repository.jpa;

import com.github.jetqin.domain.jpa.Role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jet
 *
 */

public interface RoleRepository extends JpaRepository<Role, String>
{
  // Account findAccountByAccountId(String id);
}
