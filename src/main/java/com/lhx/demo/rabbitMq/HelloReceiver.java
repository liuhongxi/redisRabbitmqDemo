package com.lhx.demo.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhx.demo.entity.User;
import com.lhx.demo.service.MailService;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
	protected static Logger logger = LoggerFactory.getLogger(HelloReceiver.class);

	@Autowired
	private MailService mailService;
	
	@RabbitHandler
	public void process(User user) {
		
		logger.debug("HelloReceiver : " + user.getUsername());
	}
}