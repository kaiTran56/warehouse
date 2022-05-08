package com.tranquyet.sup.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.converts.OrderScheduleConvert;
import com.tranquyet.sup.domains.OrderScheduleQuery;
import com.tranquyet.sup.domains.OrderScheduleSub;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.entities.OrderScheduleEntity;
import com.tranquyet.sup.enums.StatusOrderEnum;
import com.tranquyet.sup.repo.custom.OrderScheduleRepository;
import com.tranquyet.sup.service.OrderScheduleService;
import com.tranquyet.sup.utils.CustomFormatDate;

@Service
public class OrderScheduleServiceImpl implements OrderScheduleService {
	@Autowired
	private OrderScheduleConvert orderConvert;
	@Autowired
	private OrderScheduleRepository orderScheduleRepo;

	@Override
	public void save(List<OrderScheduleDTO> orders) {
		// TODO Auto-generated method stub
		orderScheduleRepo.saveAll(orders.stream().map(p -> orderConvert.toEntity(p)).collect(Collectors.toList()));

	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderScheduleDTO> getAll() {
		List<OrderScheduleDTO> orders = orderScheduleRepo.getAll().stream().map(p -> orderConvert.toDTO(p))
				.collect(Collectors.toList());
//		CSVHelper.writeCsvFile(orders);
		return orders;
	}

	@Override
	public List<OrderScheduleDTO> getByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderScheduleDTO getById(Long id) {
		return orderConvert.toDTO(orderScheduleRepo.findById(id).get());
	}

	@Override
	public List<OrderScheduleDTO> getOntimeSchedule(Integer hepaTime, Integer normalTime) {
		List<OrderScheduleDTO> orders = orderScheduleRepo.getOrderOnSchedule(hepaTime, normalTime).stream()
				.map(p -> orderConvert.toDTO(p)).collect(Collectors.toList());
		return orders;
	}

	@Override
	public List<OrderScheduleDTO> getByPhoneAndProductCode(OrderScheduleQuery query) {
		List<OrderScheduleDTO> orders = orderScheduleRepo
				.getByPhoneAndProductCode(query.getPhone(), query.getProductCode()).stream()
				.map(p -> orderConvert.toDTO(p)).collect(Collectors.toList());
		return orders;
	}

	@Override
	public void save(OrderScheduleDTO dto) {
		OrderScheduleEntity temp = null;
		if (dto.getId() != null) {
			OrderScheduleDTO oldOrSche = getById(dto.getId());
			oldOrSche = dto;
			temp = orderConvert.toEntity(oldOrSche);
		} else {
			temp = orderConvert.toEntity(dto);
			temp.setTimeRelease(CustomFormatDate.convertDateToLocalDateTime(new Date()));
			temp.setDeleteStatus(1);
			temp.setProductNote(null);
			temp.setCustomerNote(null);
			temp.setOrderNote(null);
			temp.setStatusOrderSchedule(StatusOrderEnum.WAITING.getValue());
		}
		orderScheduleRepo.save(temp);
	}

	@Override
	public void updateActionOrderSchedule(OrderScheduleSub dto) throws Exception {
		if (dto == null) {
			return;
		}
		StatusOrderEnum statusEnum = StatusOrderEnum.fromValue(dto.getStatusOrderSchedule());
		OrderScheduleDTO oldOrSche = getById(dto.getId());
		oldOrSche.update(dto);
		switch (statusEnum) {
		case COMPLETE -> {
			save(oldOrSche);
			oldOrSche.setId(null);
			save(oldOrSche);
		}
		case DENY -> {
			save(oldOrSche);
			oldOrSche.setId(null);
			save(oldOrSche);
		}
		case WAITING -> {
			save(oldOrSche);
		}
		default -> {
			throw new Exception("Cannot get status action");
		}

		}

	}

}
