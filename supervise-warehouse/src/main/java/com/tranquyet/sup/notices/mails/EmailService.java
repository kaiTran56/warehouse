package com.tranquyet.sup.notices.mails;

import javax.mail.MessagingException;

import com.tranquyet.sup.dtos.TargetInfoDTO;

public interface EmailService {
	void sendNormalMessage(TargetInfoDTO dto);

	void sendAttachmentMessage(TargetInfoDTO dto) throws MessagingException;
}
