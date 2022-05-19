package com.tranquyet.sup.operations.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.entities.StowingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.repository.StowingRepository;

@Service
@Transactional
public class StowingServiceImpl implements StowingService {
	@Autowired
	private StowingRepository stowRepo;

	@Override
	public List<StowingOrderEntity> getAll() {
		// TODO Auto-generated method stub
		return stowRepo.getAll();
	}

	@Override
	public StowingOrderEntity getById(Long id) {
		// TODO Auto-generated method stub
		return stowRepo.findById(id).get();
	}

	@Override
	public StowingOrderEntity save(StowingOrderEntity picking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(OrderStatus orderStatus) throws Exception {
		stowRepo.updateStatus(orderStatus.getStatus(), orderStatus.getId());

	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

}
