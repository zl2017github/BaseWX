package cn.com.et.service.bike.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.bike.BikeHistory;
import cn.com.et.entity.bike.BikeInfo;
import cn.com.et.service.bike.BikeHistoryService;

@Service
@Transactional
public class BikeHistoryServiceImpl implements BikeHistoryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<BikeHistory> getByCustomerId(Integer customerId)
			throws Exception {
		return (List<BikeHistory>)dao.findForList("BikeHistoryMapper.getByCustomerId", customerId);
	}

	public Integer save(BikeHistory bikeHistory) throws Exception {
		return (Integer)dao.saveReturnId("BikeHistoryMapper.save", bikeHistory);
	}

	public BikeHistory getById(Integer id) throws Exception {
		return (BikeHistory)dao.findForObject("BikeHistoryMapper.getById", id);
	}

	public void updateFinishBike(BikeHistory bikeHistory) throws Exception {
		dao.update("BikeHistoryMapper.updateFinishBike", bikeHistory);
	}

	public void updateToIsNotvalid(Integer customerId) throws Exception {
		List<BikeHistory> historylist = this.getByCustomerId(customerId);
		if(historylist.size()>0){
			BikeHistory history = historylist.get(0);
			dao.update("BikeHistoryMapper.updateToIsNotvalid", history.getId());
		}
	}
}
