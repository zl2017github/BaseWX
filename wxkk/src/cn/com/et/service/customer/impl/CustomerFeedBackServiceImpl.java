package cn.com.et.service.customer.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.CustomerFeedBack;
import cn.com.et.service.customer.CustomerFeedBackService;

@Service
@Transactional
public class CustomerFeedBackServiceImpl implements CustomerFeedBackService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public Integer save(CustomerFeedBack customerFeedBack) throws Exception {
		dao.saveReturnId("CustomerFeedBackMapper.save", customerFeedBack);
		return customerFeedBack.getId();
	}

}
