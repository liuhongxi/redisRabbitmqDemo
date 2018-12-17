package com.lhx.demo.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhx.demo.entity.User;

@Component
public class HelloSender {

	protected static Logger logger=LoggerFactory.getLogger(HelloSender.class);

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public String send(User user) {
		logger.info("HelloSender："+user.getUsername());
		this.amqpTemplate.convertAndSend("hello",user);
		return user.getUsername()+"  的信息已推送完成";
	}
		
}
