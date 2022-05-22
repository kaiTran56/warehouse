package com.tranquyet.sup.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.tranquyet.sup.algorithm.model.TaskQuery;
import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.system_settings.entities.PodEntity;

@Component
public class AlgorithmCenter {

	public List<List<TaskQuery>> executeAlgorithm(List<PodEntity> pods, List<ProductEntity> products) {
		List<List<TaskQuery>> taskSet = new ArrayList<>();
		List<TaskQuery> tasks = new ArrayList<>();
		Integer numbPod = pods.size();
//		Integer numbProduct = products.size();

		products.stream().filter(p -> p != null).forEach(p -> {
			IntStream.range(0, numbPod).forEach(indx -> {
				PodEntity pod = pods.get(indx);
				if (p.getWidth() == (int) Math.round(pod.getWidth())
						&& p.getHeight() == (int) Math.round(pod.getHeight())
						&& p.getLength() == (int) Math.round(pod.getLength())) {
					if (pod.getStorageQuantity() == tasks.size()) {
						taskSet.add(tasks);
						tasks.clear();
					}
					TaskQuery task = new TaskQuery();
					task.setIdPod(pod.getId());
					task.setIdProduct(p.getId());
					tasks.add(task);
				}
			});

		});
		return taskSet;

	}
}
