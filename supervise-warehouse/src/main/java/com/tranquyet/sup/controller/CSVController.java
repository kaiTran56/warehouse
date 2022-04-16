package com.tranquyet.sup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.constants.CsvConstants;
import com.tranquyet.sup.service.CSVService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/csv")
public class CSVController {
	@Autowired
	CSVService fileService;

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = CsvConstants.createCsvName();
		InputStreamResource file = new InputStreamResource(fileService.load());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}
}