package com.tranquyet.sup.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "target_info")
public class TargetInfoEntity extends BasedEntity {
	private String sender;
	private String receiver;
	private String subject;
	private String content;
	private String attachment;
}
