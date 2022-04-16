package com.tranquyet.sup.notices.mails;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.constants.CsvConstants;
import com.tranquyet.sup.dtos.TargetInfoDTO;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendNormalMessage(TargetInfoDTO dto) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendAttachmentMessage(TargetInfoDTO dto) throws MessagingException {
		MimeMessage msg = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(dto.getReceiver());
		helper.setSubject(dto.getSubject());
		helper.setText(dto.getContent(), true);
		FileSystemResource file2 = new FileSystemResource(new File(dto.getAttachment()));
		helper.addAttachment(CsvConstants.createCsvName(), file2);
		emailSender.send(msg);
	}

}
