package com.tranquyet.sup.system_settings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.system_settings.entities.PodEntity;
import com.tranquyet.sup.system_settings.service.PodService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pod")
public class PodController {
	@Autowired
	private PodService podService;

	@GetMapping("/list")
	public ResponseEntity<PodEntity> getPods() {
		List<PodEntity> entities = podService.getAll();
		PodEntity entity = new PodEntity();
		entity.setListResult(entities);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody PodEntity entity) {
		podService.save(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPod(@PathVariable Long id) {
		PodEntity entity = podService.getById(id);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<List<ProductEntity>> getProductOfPod(@PathVariable Long id) {
		List<ProductEntity> products = podService.getProductByPodId(id);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Long[] ids = { id };
		if (podService.checkWorking(id)) {
			return new ResponseEntity<>("POD has been used!", HttpStatus.CONFLICT);
		}
		podService.delete(ids);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}
}
