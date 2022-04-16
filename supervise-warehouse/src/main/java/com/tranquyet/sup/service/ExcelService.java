package com.tranquyet.sup.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tranquyet.sup.converts.OrderScheduleConvert;
import com.tranquyet.sup.entities.OrderScheduleEntity;
import com.tranquyet.sup.repo.custom.OrderScheduleRepository;
import com.tranquyet.sup.utils.ExcelHelper;

@Service
public class ExcelService {
	@Autowired
	private OrderScheduleRepository orderScheduleRepo;

	@Autowired
	private OrderScheduleConvert orderScheduleConvert;

	public void save(MultipartFile file) throws IOException {
		List<OrderScheduleEntity> orders = ExcelHelper.saveExcelToDatabase(file.getInputStream()).stream()
				.map(p -> orderScheduleConvert.toEntity(p)).collect(Collectors.toList());
		orderScheduleRepo.saveAll(orders);
	}
}
