package com.tranquyet.sup.statistics.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_logs")
@Getter
@Setter
public class UserLogEntity extends BasedEntity {
	@Column
	private Long idUser;
	@Column
	private String action;
}
