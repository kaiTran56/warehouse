package com.tranquyet.sup.product_managements.controller;

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
import com.tranquyet.sup.product_managements.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductService prodService;

	@GetMapping("/list")
	public ResponseEntity<ProductEntity> getPods() {
		List<ProductEntity> entities = prodService.getAll();
		ProductEntity entity = new ProductEntity();
		entity.setListResult(entities);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductEntity entity) {
		prodService.save(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPod(@PathVariable Long id) {
		ProductEntity entity = prodService.getById(id);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Long[] ids = { id };
		if (prodService.checkWorking(id)) {
			return new ResponseEntity<>("Product has been used!", HttpStatus.CONFLICT);
		}
		prodService.delete(ids);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}
}
