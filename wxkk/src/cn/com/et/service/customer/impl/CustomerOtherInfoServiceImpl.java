package cn.com.et.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.CustomerOtherInfo;
import cn.com.et.service.customer.CustomerOtherInfoService;

@Service
@Transactional
public class CustomerOtherInfoServiceImpl implements CustomerOtherInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public Integer save(CustomerOtherInfo customerOtherInfo) throws Exception {
		dao.saveReturnId("CustomerOtherInfoMapper.save", customerOtherInfo);
		return customerOtherInfo.getId();
	}

	public CustomerOtherInfo getByIdNum(String idNumber) throws Exception {
		List<CustomerOtherInfo> cusList = (List<CustomerOtherInfo>)dao.findForList("CustomerOtherInfoMapper.getByIdNum", idNumber);
		if(cusList.size()>0){
			return cusList.get(0);
		}
		return null;
	}

}
