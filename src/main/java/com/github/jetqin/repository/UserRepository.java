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

package com.github.jetqin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.jetqin.domain.User;

/**
 * @author jet
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
//	@Query("from User as u inner join fetch u.roles where u.userId = ?1")
//	public User findUserByUserId(String userId);
	
//	@Query("select u from User as u left join fetch u.roles where u.username = ?1")
	public User findUserByUsername(String username);
	
//	@Query("select a.roleName from Role a where a.roleId in (select roleId from UserRole b where b.username = ?1)")
//	public List<String> findRoleByUserId(String username);
}
