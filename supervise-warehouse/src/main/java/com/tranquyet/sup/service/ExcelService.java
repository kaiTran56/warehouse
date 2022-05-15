package com.tranquyet.sup.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tranquyet.sup.converts.OrderScheduleConvert;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.entities.OrderScheduleEntity;
import com.tranquyet.sup.repository.OrderScheduleRepository;
import com.tranquyet.sup.utils.ExcelHelper;

@Service
public class ExcelService {
	@Autowired
	private OrderScheduleRepository orderScheduleRepo;

	@Autowired
	private OrderScheduleService orderScheSer;

	@Autowired
	private OrderScheduleConvert orderScheduleConvert;

	public void save(MultipartFile file) throws IOException {
		List<OrderScheduleEntity> orders = ExcelHelper.saveExcelToDatabase(file.getInputStream()).stream()
				.map(p -> orderScheduleConvert.toEntity(p)).collect(Collectors.toList());
		orderScheduleRepo.saveAll(orders);
	}

	public List<OrderScheduleDTO> validateOrdScheImport(MultipartFile file) throws IOException {
		List<OrderScheduleDTO> ordTemps = new ArrayList<>();
		List<OrderScheduleDTO> orders = ExcelHelper.saveExcelToDatabase(file.getInputStream()).stream()
				.collect(Collectors.toList());
		List<OrderScheduleDTO> currOrders = orderScheSer.getAll();
		if (currOrders.size() == 0) {
			return orders;
		}
		orders.forEach(p1 -> {
			OrderScheduleDTO obj = checkExistOrdSche(p1);
			if (obj == null) {
				ordTemps.add(p1);
			}
		});

		return ordTemps;
	}

	public OrderScheduleDTO checkExistOrdSche(OrderScheduleDTO dto) {
		List<OrderScheduleDTO> currOrders = orderScheduleRepo.checkExist().stream()
				.map(p -> orderScheduleConvert.toDTO(p)).collect(Collectors.toList());
		return currOrders.stream()
				.filter(p -> p.getProductCode().equals(dto.getProductCode())
						&& p.getOrderCode().equals(dto.getOrderCode()) && p.getPhone().equals(dto.getPhone())
						&& p.getTimeRelease().equals(dto.getTimeRelease()))
				.findFirst().orElse(null);

	}
}
