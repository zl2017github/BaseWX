package cn.com.et.service.bike.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.bike.BikeReport;
import cn.com.et.service.bike.BikeReportService;

@Service
@Transactional
public class BikeReportServiceImpl implements BikeReportService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public Integer save(BikeReport report) throws Exception {
		dao.saveReturnId("BikeReportMapper.save", report);
		return report.getId();
	}
}
