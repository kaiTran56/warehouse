package com.tranquyet.common.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BasedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5310396676848615355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "createdDate")
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "modifiedDate")
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;
	@Column(name = "modifiedBy")
	@LastModifiedBy
	private String modifiedBy;
	@Column(name = "deleteStatus", columnDefinition = "INT")
	private Integer deleteStatus;
	@Column(name = "working", columnDefinition = "INT")
	private Integer working;
	@Column(name = "status")
	private String status;

}
