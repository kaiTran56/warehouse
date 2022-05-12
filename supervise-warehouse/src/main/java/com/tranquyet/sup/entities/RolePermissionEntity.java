package com.tranquyet.sup.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles_permissions")
@Getter
@Setter
public class RolePermissionEntity {
	@EmbeddedId
	private PrimaryKey keys;

	@Getter
	@Setter
	@Embeddable
	public static class PrimaryKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long permissionsId;
		private Long rolesId;

	}
}
