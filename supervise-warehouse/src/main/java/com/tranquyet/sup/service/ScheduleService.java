package com.tranquyet.sup.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tranquyet.sup.dtos.TargetInfoDTO;
import com.tranquyet.sup.notices.mails.EmailService;
import com.tranquyet.sup.utils.CSVHelper;

@Component
public class ScheduleService {
	@Autowired
	private OrderScheduleService orderService;
	@Autowired
	private EmailService emailService;

//	@Scheduled(cron = "* * * * * ?")
//	@Scheduled(fixedDelay = 100000)
	public void scheduleTaskUsingCronExpression() {
		TargetInfoDTO email = new TargetInfoDTO();
		email.setSender("chigodka11@gmail.com");
		email.setReceiver("quyettran057@gmail.com");
		email.setContent("Check!");
		email.setSubject("Check check");
		String path = CSVHelper.writeCsvFile(orderService.getAll());
		email.setAttachment(path);
		try {
			emailService.sendAttachmentMessage(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
