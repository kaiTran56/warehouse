package com.tranquyet.sup.dtos;

import com.tranquyet.common.dtos.BasedDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TargetInfoDTO extends BasedDTO<TargetInfoDTO> {
	private String sender;
	private String receiver;
	private String subject;
	private String content;
	private String attachment;

//	public String getAttachment() {
//		return this.attachment.replace(CsvConstants.DIR_ROOT, "");
//	}
}
