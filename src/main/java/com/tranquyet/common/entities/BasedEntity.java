package com.tranquyet.common.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public abstract class BasedEntity {

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
	@Transient
	private List<?> listResult = new ArrayList<>();

}
