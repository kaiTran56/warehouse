package com.tranquyet.sup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.domains.MessageDomain;
import com.tranquyet.sup.service.WSService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WSController {

	@Autowired
	private WSService service;

	@PostMapping("/send-message")
	public void sendMessage(@RequestBody final MessageDomain message) {
		log.info("WSController: " + message.toString());
		service.notifyFrontend(message.getMessageContent());
	}
}