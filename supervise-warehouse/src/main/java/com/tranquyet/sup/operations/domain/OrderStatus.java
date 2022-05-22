package com.tranquyet.sup.operations.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatus {
	private Long id;
	private Long idPod;
	private String status;
	private String qrCodeProduct;
	private String qrCodePod;
}
