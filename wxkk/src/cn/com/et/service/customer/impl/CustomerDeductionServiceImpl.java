package cn.com.et.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.CustomerDeduction;
import cn.com.et.service.customer.CustomerDeductionService;

@Service
@Transactional
public class CustomerDeductionServiceImpl implements CustomerDeductionService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public CustomerDeduction getByCustomerId(Integer customerId) throws Exception {
		List<CustomerDeduction> dedutionList = (List<CustomerDeduction>)dao.findForList("CustomerDeductionMapper.getByCustomerId", customerId);
		if(dedutionList != null && dedutionList.size() > 0){
			return dedutionList.get(0);
		}
		return null;
	}

	public void updatePrice(CustomerDeduction deduction) throws Exception {
		dao.update("CustomerDeductionMapper.updatePrice", deduction);
	}

	public Integer save(CustomerDeduction deduction) throws Exception {
		dao.save("CustomerDeductionMapper.save", deduction);
		return deduction.getId();
	}
	
	

}
