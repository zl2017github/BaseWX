package cn.com.et.service.house.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.plugin.page.Page;
import cn.com.et.plugin.page.PageData;
import cn.com.et.service.house.HouseInfoService;

@Service
@Transactional
public class HouseInfoServiceImpl implements HouseInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<PageData> houseInfoList(Page page) throws Exception {
		return (List<PageData>)dao.findForList("HouseInfoMapper.houseInfoList", page);
	}
}
