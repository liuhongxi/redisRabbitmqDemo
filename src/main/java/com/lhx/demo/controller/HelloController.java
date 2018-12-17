package com.lhx.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhx.demo.entity.User;
import com.lhx.demo.rabbitMq.HelloSender;

@RestController
public class HelloController {
	protected static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private HelloSender helloSender;

	@PostMapping("/send/userMq")
	public String helloworld(User user) {
		return helloSender.send(user);
	}
}
