package com.tranquyet.sup.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class PermissionEntity extends BasedEntity {

	@Column(unique = true, nullable = false)
	private String name;
	@Column
	private String description;
	@ManyToMany
	List<RoleEntity> roles;
}
