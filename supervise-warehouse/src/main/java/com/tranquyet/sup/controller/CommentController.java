package com.tranquyet.sup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.tranquyet.sup.domains.MessageDomain;
import com.tranquyet.sup.domains.ResponseMessage;
import com.tranquyet.sup.statistics.service.UserLogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommentController {

	@Autowired
	private UserLogService commentService;

	@MessageMapping("/message")
	@SendTo("/topic/messages")
	public ResponseMessage getMessage(final MessageDomain message) throws InterruptedException {
		Thread.sleep(1000);
		log.info("Comment Controller: " + message.toString());
//        commentService.customSaveMessage(message);
		return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()), "");
	}

}