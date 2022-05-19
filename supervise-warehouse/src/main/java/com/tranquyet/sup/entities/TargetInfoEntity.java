package com.tranquyet.sup.entities;

import javax.persistence.Column;
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

	@Column
	private String sender;
	@Column
	private String receiver;
	@Column
	private String subject;
	@Column
	private String content;
	@Column
	private String attachment;
}
