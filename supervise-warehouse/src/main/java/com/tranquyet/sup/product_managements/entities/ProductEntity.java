package com.tranquyet.sup.product_managements.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;
import com.tranquyet.sup.system_settings.entities.PodEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity extends BasedEntity {
	@Column(unique = true, nullable = false)
	private String qrCode;
	@Column
	private String nameProduct;
	@Column
	private Integer quantity;
	@Column
	private Double price;
	@Column
	private Double length;
	@Column
	private Double width;
	@Column
	private Double height;
	@Column
	private String urlImage;
	@ManyToOne
	@JoinColumn(name = "pods_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private PodEntity pod;
}
