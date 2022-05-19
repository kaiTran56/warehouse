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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BasedEntity {

	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String gmail;
	@Column
	private String password;
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "usersId"), inverseJoinColumns = @JoinColumn(name = "rolesId"))
	List<RoleEntity> roles;

}
