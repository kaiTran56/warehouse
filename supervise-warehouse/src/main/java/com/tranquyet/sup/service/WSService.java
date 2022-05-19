package com.tranquyet.sup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.domains.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WSService {

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public WSService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	public void notifyFrontend(final String message) {
		log.info("WSService: " + message);
		ResponseMessage response = new ResponseMessage();
		response.setContent(message);
		messagingTemplate.convertAndSend("/topic/messages", response);
	}

}