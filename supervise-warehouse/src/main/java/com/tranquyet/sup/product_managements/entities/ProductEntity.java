package com.tranquyet.sup.product_managements.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	@NotEmpty
	@Length(max = 13)
	private String qrCode;
	@Column
	@NotEmpty
	@Length(max = 13)
	private String nameProduct;
	@Column
	private Integer quantity;
	@Column
	private Double price;
	@Column
	private Integer length;
	@Column
	private Integer width;
	@Column
	private Integer height;
	@Column
	private String urlImage;
	@ManyToOne
	@JoinColumn(name = "pods_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private PodEntity pod;
	@Column
	private String note;
}
