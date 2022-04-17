package com.tranquyet.sup.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.dtos.TargetInfoDTO;
import com.tranquyet.sup.enums.TimeRelease;
import com.tranquyet.sup.notices.mails.EmailService;
import com.tranquyet.sup.utils.CSVHelper;

@Component
public class ScheduleService {
	@Autowired
	private OrderScheduleService orderService;
	@Autowired
	private EmailService emailService;
	@Value("${admin.email.info}")
	private String receiver;

	@Scheduled(cron = "0 0 8,15 * * *")
//	@Scheduled(fixedDelay = 100000)
	public void scheduleTaskUsingCronExpression() {
		List<OrderScheduleDTO> dtos = orderService.getOntimeSchedule(TimeRelease.HEPA_TIME.getKey(),
				TimeRelease.NORMAL_TIME.getKey());
		Optional.of(dtos).ifPresent(p -> {
			TargetInfoDTO email = new TargetInfoDTO();
			email.setSender("chigodka11@gmail.com");
			email.setReceiver(receiver);
			email.setContent("List of on time orders");
			email.setSubject("On time orders");
			String path = CSVHelper.writeCsvFile(dtos);
			email.setAttachment(path);
			try {
				emailService.sendAttachmentMessage(email);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});

	}
}
