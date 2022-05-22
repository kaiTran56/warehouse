package com.tranquyet.sup.algorithm.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskQuery {
	private Map<Long, Long> podStorages = new HashMap<>();
	private Long idProduct;
	private Long idPod;

	public void addTask(Long idProduct, Long idPod) {
		podStorages.put(idPod, idProduct);
	}
}
