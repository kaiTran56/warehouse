package com.tranquyet.sup.operations.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.entities.StocktakingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.repository.StocktakingRepository;

@Service
@Transactional
public class StocktakingServiceImpl implements StocktakingService {

	@Autowired
	private StocktakingRepository stocktakingRepo;

	@Override
	public List<StocktakingOrderEntity> getAll() {
		// TODO Auto-generated method stub
		return stocktakingRepo.getAll();
	}

	@Override
	public StocktakingOrderEntity getById(Long id) {
		// TODO Auto-generated method stub
		return stocktakingRepo.findById(id).get();
	}

	@Override
	public StocktakingOrderEntity save(StocktakingOrderEntity picking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(OrderStatus orderStatus) throws Exception {
		stocktakingRepo.updateStatus(orderStatus.getStatus(), orderStatus.getId());

	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

}
