package cn.com.et.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.Customer;
import cn.com.et.service.customer.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public Customer getByWechatId(String wechatId) throws Exception {
		List<Customer> customerList = (List<Customer>)dao.findForList("CustomerMapper.getByWechatId", wechatId);
		if(customerList != null && customerList.size() > 0){
			return customerList.get(0);
		}
		return null;
	}

	public Customer login(Customer customer) throws Exception {
		return (Customer)dao.findForObject("CustomerMapper.login", customer);
	}

	public Customer getByMobile(String mobile) throws Exception {
		List<Customer> customerList = (List<Customer>)dao.findForList("CustomerMapper.getByMobile", mobile);
		if(customerList != null && customerList.size() > 0){
			return customerList.get(0);
		}
		return null;
	}

	public void updateWechatId(Customer customer) throws Exception {
		dao.update("CustomerMapper.updateWechatId", customer);
	}

	public void updateCustomerIsUsed(Customer customer) throws Exception {
		dao.update("CustomerMapper.updateCustomerIsUsed", customer);
	}

	public Integer save(Customer customer) throws Exception {
		dao.saveReturnId("CustomerMapper.save", customer);
		return customer.getId();
	}

	public void updateCustomerIsCertification(Customer customer)
			throws Exception {
		dao.update("CustomerMapper.updateCustomerIsCertification", customer);
	}

	public void updateCustomerIsPayDeposit(Customer customer)
			throws Exception {
		dao.update("CustomerMapper.updateCustomerIsPayDeposit", customer);
	}
}
