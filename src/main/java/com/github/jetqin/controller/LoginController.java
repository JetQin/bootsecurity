package com.github.jetqin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.jetqin.controller.common.Constants;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
}
