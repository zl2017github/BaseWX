package cn.com.et.service.bike.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.bike.BikeProcessFlow;
import cn.com.et.service.bike.BikeProcessFlowService;

@Service
@Transactional
public class BikeProcessFlowServiceImpl implements BikeProcessFlowService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public List<BikeProcessFlow> bikeProcessFlowList(BikeProcessFlow bikeProcessFlow) throws Exception {
		return (List<BikeProcessFlow>)dao.findForList("BikeProcessFlowMapper.processFlowlist", bikeProcessFlow);
	}

	public Integer save(BikeProcessFlow flow) throws Exception {
		dao.save("BikeProcessFlowMapper.save", flow);
		return flow.getId();
	}
}
