package com.tranquyet.sup.operations.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tranquyet.sup.constants.CsvConstants;
import com.tranquyet.sup.domains.OrderScheduleQuery;
import com.tranquyet.sup.domains.OrderScheduleSub;
import com.tranquyet.sup.domains.ResponseMessage;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.service.CSVService;
import com.tranquyet.sup.service.ExcelService;
import com.tranquyet.sup.service.OrderScheduleService;
import com.tranquyet.sup.utils.ExcelHelper;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order-schedule")
@Slf4j
public class OrderScheduleController {
	@Autowired
	private OrderScheduleService orderScheduleService;
	@Autowired
	private ExcelService fileService;
	@Autowired
	private CSVService csvFileService;

	@GetMapping
	public ResponseEntity<OrderScheduleDTO> renderOrderSchedules() {
		OrderScheduleDTO order = new OrderScheduleDTO();
		order.setListResult(orderScheduleService.getAll());
		Optional<OrderScheduleDTO> orderOp = Optional.of(order);
		return new ResponseEntity<>(orderOp.get(), orderOp.isPresent() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<OrderScheduleDTO> getDetailOrderSchedule(@PathVariable(required = true) Long id) {
		final HttpStatus[] httpStatuses = { null };
		Optional<OrderScheduleDTO> dto = Optional.of(orderScheduleService.getById(id));
		dto.ifPresent(p -> {
			httpStatuses[0] = HttpStatus.OK;
		});
		return new ResponseEntity<>(dto.get(), Optional.of(httpStatuses[0]).orElse(HttpStatus.EXPECTATION_FAILED));
	}

	@PostMapping("/search")
	public ResponseEntity<OrderScheduleDTO> getListOrderSchedule(
			@RequestBody(required = true) OrderScheduleQuery query) {
		final HttpStatus[] httpStatuses = { null };
		Optional<List<OrderScheduleDTO>> schedules = Optional.of(orderScheduleService.getByPhoneAndProductCode(query));
		OrderScheduleDTO order = new OrderScheduleDTO();
		order.setListResult(schedules.get());
		Optional.of(order).ifPresent(p -> {
			httpStatuses[0] = HttpStatus.OK;
		});
		return new ResponseEntity<>(order, Optional.of(httpStatuses[0]).orElse(HttpStatus.EXPECTATION_FAILED));

	}

	@PostMapping("/update")
	public ResponseEntity<?> updateOrderSchedule(@RequestBody(required = true) OrderScheduleSub query) {
		try {
			orderScheduleService.updateActionOrderSchedule(query);
			return new ResponseEntity<>(query, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveListOrdSche(@RequestBody List<OrderScheduleDTO> dtos) {
		try {
			orderScheduleService.save(dtos);
			return new ResponseEntity<>("Done", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info(ex + "");
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/import/excel")
	public ResponseEntity<?> uploadFileTemp(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				List<OrderScheduleDTO> orders = fileService.validateOrdScheImport(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(orders);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, null));
			}
		}
		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, null));
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = CsvConstants.createCsvName();
		InputStreamResource file = new InputStreamResource(csvFileService.load());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}
}
