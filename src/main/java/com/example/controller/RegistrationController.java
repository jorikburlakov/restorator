/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.controller;

import com.example.enums.UserType;
import com.example.orm.model.security.Role;
import com.example.orm.model.User;
import com.example.orm.repository.RoleRepository;
import com.example.orm.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
public class RegistrationController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private Gson gson;

	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public String home(@RequestBody String body) {
		User user = gson.fromJson(body, User.class);
		for(Role role:roleRepository.findAll()){
			if(UserType.ROLE_USER.equals(role.getName())){
				user.getRoles().add(role);
				user.setPassword(encoder.encode(user.getPassword()));
				userRepository.save(user);
				return "ok";
			}
		}
		return "error";
	}

}
