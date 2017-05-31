package cn.com.et.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.Customer;
import cn.com.et.entity.house.HouseCustomerInfo;
import cn.com.et.service.customer.CustomerService;
import cn.com.et.service.customer.HouseCustomerInfoService;

@Service
@Transactional
public class HouseCustomerInfoServiceImpl implements HouseCustomerInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public HouseCustomerInfo getByUserMobile(String mobile) throws Exception {
		List<HouseCustomerInfo> customerList = (List<HouseCustomerInfo>)dao.findForList("CustomerMapper.getByUserMobile", mobile);
		if(customerList != null && customerList.size() > 0){
			return customerList.get(0);
		}
		return null;
	}

}
