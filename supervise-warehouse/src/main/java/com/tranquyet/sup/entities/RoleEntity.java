package com.tranquyet.sup.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity extends BasedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6594685889575880638L;
	@Column(unique = true, nullable = false)
	private String name;
	@Column
	private String description;
	@ManyToMany
	private List<UserEntity> users;

	@ManyToMany
	@JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "rolesId"), inverseJoinColumns = @JoinColumn(name = "permissionsId"))
	List<PermissionEntity> permissions;

}
