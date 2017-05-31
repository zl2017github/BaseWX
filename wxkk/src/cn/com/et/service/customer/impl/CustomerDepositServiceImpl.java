package cn.com.et.service.customer.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.CustomerDeposit;
import cn.com.et.service.customer.CustomerDepositService;

@Service
@Transactional
public class CustomerDepositServiceImpl implements CustomerDepositService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public Integer save(CustomerDeposit deposit) throws Exception {
		dao.saveReturnId("CustomerDepositMapper.save", deposit);
		return deposit.getId();
	}

}
