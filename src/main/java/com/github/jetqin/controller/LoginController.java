package com.github.jetqin.controller;

import com.github.jetqin.repository.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;

import com.github.jetqin.controller.common.Constants;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	@Qualifier("restTemplate")
	private OAuth2RestTemplate restTemplate;

//	@Autowired
//    @Qualifier("passwordEncoder")
//    PasswordEncoder passwordEncoder;

	@RequestMapping("/obtainToken")
	@ResponseBody
	public ResponseEntity<String> obtainAccessToken(
			@RequestParam(defaultValue="",name="userName")String userName,
			@RequestParam(defaultValue="",name="password")String password) {
		logger.info(String.format("username=%s,password=%s", userName,password));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(Constants.OK,headers,HttpStatus.OK);
	}

	@RequestMapping("/login/account")
	@ResponseBody
	public ResponseEntity<String> loginAccount(@RequestParam(defaultValue="",name="userName")String userName,
			@RequestParam(defaultValue="",name="password")String password,
			@RequestParam(defaultValue="",name="type")String type) {
		logger.info(String.format("username=%s,password=%s,type=%s", userName,password,type));
	    HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);  
		return new ResponseEntity<String>(Constants.OK,headers,HttpStatus.OK);
	}

//	@RequestMapping(value = "/signup",method = {RequestMethod.GET, RequestMethod.POST})
//	public ResponseEntity<String> signup(@RequestBody(required = true) User user)
//	{
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		userRepository.save(user);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		return new ResponseEntity<String>(Constants.OK,headers,HttpStatus.OK);
//	}
}
