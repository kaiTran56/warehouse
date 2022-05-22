package com.tranquyet.sup.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.algorithm.AlgorithmCenter;
import com.tranquyet.sup.constants.CsvConstants;
import com.tranquyet.sup.dtos.TargetInfoDTO;
import com.tranquyet.sup.enums.TimeRelease;
import com.tranquyet.sup.notices.mails.EmailService;
import com.tranquyet.sup.product_managements.service.ProductService;
import com.tranquyet.sup.service.CSVService;
import com.tranquyet.sup.service.OrderScheduleService;
import com.tranquyet.sup.system_settings.service.PodService;
import com.tranquyet.sup.utils.CSVHelper;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/csv")
@Slf4j
public class CSVController {
	@Autowired
	CSVService fileService;

	@Autowired
	private AlgorithmCenter algo;

	@Autowired
	private PodService podService;

	@Autowired
	private ProductService productService;

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {

		String filename = CsvConstants.createCsvName();
		InputStreamResource file = new InputStreamResource(fileService.load());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	@Autowired
	private OrderScheduleService orderService;
	@Autowired
	private EmailService emailService;
	@Value("${admin.email.info}")
	private String receiver;

	@Autowired
	private OrderScheduleService service;

	@GetMapping
	public ResponseEntity<?> get() {
		log.info(algo.executeAlgorithm(podService.getAll(), productService.getAll()) + "");
		TargetInfoDTO email = new TargetInfoDTO();
		email.setSender("chigodka11@gmail.com");
		email.setReceiver(receiver);
		email.setContent("Check!");
		email.setSubject("Check check");
		String path = CSVHelper.writeCsvFile(
				orderService.getOntimeSchedule(TimeRelease.HEPA_TIME.getKey(), TimeRelease.NORMAL_TIME.getKey()));
		email.setAttachment(path);
		try {
			emailService.sendAttachmentMessage(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(
				service.getOntimeSchedule(TimeRelease.HEPA_TIME.getKey(), TimeRelease.NORMAL_TIME.getKey()),
				HttpStatus.OK);
	}
}