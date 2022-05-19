package com.tranquyet.sup.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tranquyet.sup.product_managements.service.ProductService;
import com.tranquyet.sup.system_settings.service.PodService;

@Component
public class AlgorithmCenter {
	@Autowired
	private PodService podSer;
	@Autowired
	private ProductService productSer;
}
