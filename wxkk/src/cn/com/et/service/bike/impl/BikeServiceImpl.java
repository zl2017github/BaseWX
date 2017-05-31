package cn.com.et.service.bike.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.bike.BikeInfo;
import cn.com.et.service.bike.BikeService;

@Service
@Transactional
public class BikeServiceImpl implements BikeService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public BikeInfo getByBikeNum(String bikeNum) throws Exception {
		return (BikeInfo)dao.findForObject("BikeMapper.getByBikeNum", bikeNum);
	}

	public BikeInfo getById(Integer id) throws Exception {
		return (BikeInfo)dao.findForObject("BikeMapper.getById", id);
	}
}
