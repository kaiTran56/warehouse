package com.tranquyet.sup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity extends BasedEntity {
	@Column(unique = true, nullable = false)
	private String name;
	@Column
	private String description;
//	@ManyToMany
//	private Set<UserEntity> users;
//
//	@ManyToMany
//	Set<PermissionEntity> permissions;

}
