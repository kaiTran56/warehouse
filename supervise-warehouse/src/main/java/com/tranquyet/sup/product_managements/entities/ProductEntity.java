package com.tranquyet.sup.product_managements.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity extends BasedEntity {
	@Column(unique = true, nullable = false)
	private String qrCode;
	@Column
	private Integer quantity;
	@Column
	private Double price;
	@Column
	private Double width;
	@Column
	private Double height;
	@Column
	private String urlImage;
}
