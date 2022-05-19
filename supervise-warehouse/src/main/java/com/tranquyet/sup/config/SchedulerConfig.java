package com.tranquyet.sup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tranquyet.sup.domains.ResponseMessage;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public SchedulerConfig(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

//	@Scheduled(fixedRate = 8000)
	public void notifyFrontend() {
		System.out.println("Hello");
		ResponseMessage message = new ResponseMessage();
		message.setContent("[Server]: Hello World!");
		messagingTemplate.convertAndSend("/topic/messages", message);
	}

}