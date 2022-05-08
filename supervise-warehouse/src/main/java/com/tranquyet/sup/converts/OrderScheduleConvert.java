package com.tranquyet.sup.converts;

import org.springframework.stereotype.Component;

import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.entities.OrderScheduleEntity;

@Component
public class OrderScheduleConvert {
	public OrderScheduleDTO toDTO(OrderScheduleEntity entity) {
		OrderScheduleDTO dto = new OrderScheduleDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
//		dto.setNextTimeRelease(entity.getNextTimeRelease());
		dto.setDeleteStatus(entity.getDeleteStatus());
		dto.setOrderCode(entity.getOrderCode());
		dto.setTimeRelease(entity.getTimeRelease());
		dto.setSource(entity.getSource());
		dto.setStaff(entity.getStaff());
		dto.setStatusOrder(entity.getStatusOrder());
		dto.setCustomerName(entity.getCustomerName());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		dto.setProductCode(entity.getProductCode());
		dto.setProductName(entity.getProductName());
		dto.setProductNote(entity.getProductNote());
		dto.setOrderNote(entity.getOrderNote());
		dto.setOrderNote(entity.getOrderNote());
		dto.setCustomerNote(entity.getCustomerNote());
		dto.setStatusOrderSchedule(entity.getStatusOrderSchedule());
		return dto;
	}

	public OrderScheduleEntity toEntity(OrderScheduleDTO dto) {
		OrderScheduleEntity entity = new OrderScheduleEntity();
		entity.setId(dto.getId());
		entity.setDeleteStatus(dto.getDeleteStatus());
		entity.setOrderCode(dto.getOrderCode());
		entity.setTimeRelease(dto.getTimeRelease());
		entity.setSource(dto.getSource());
//		entity.setNextTimeRelease(dto.getNextTimeRelease());
		entity.setStaff(dto.getStaff());
		entity.setStatusOrder(dto.getStatusOrder());
		entity.setCustomerName(dto.getCustomerName());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		entity.setProductCode(dto.getProductCode());
		entity.setProductName(dto.getProductName());
		entity.setProductNote(dto.getProductNote());
		entity.setOrderNote(dto.getOrderNote());
		entity.setOrderNote(dto.getOrderNote());
		entity.setCustomerNote(dto.getCustomerNote());
		entity.setStatusOrderSchedule(dto.getStatusOrderSchedule());
		return entity;
	}
}
