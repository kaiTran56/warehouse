package com.tranquyet.sup.system_settings.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tranquyet.common.entities.BasedEntity;
import com.tranquyet.sup.product_managements.entities.ProductEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pods")
@Getter
@Setter
public class PodEntity extends BasedEntity {

	@NotBlank
	@NotEmpty
	@Column(unique = true, nullable = false)
	private String qrCode;
	@Column
	@NotNull
	@Min(value = 1)
	private Integer storageQuantity;
	@Column
	@NotNull
	@Min(value = 1)
	private Double width;
	@Column
	@NotNull
	@Min(value = 1)
	private Double height;
	@Column
	@NotNull
	@Min(value = 1)
	private Double length;
	@OneToMany(mappedBy = "pod", fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private List<ProductEntity> products = new ArrayList<>();
}
