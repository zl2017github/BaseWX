package cn.com.et.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.customer.CustomerDeduction;
import cn.com.et.entity.customer.CustomerWallet;
import cn.com.et.service.customer.CustomerWalletService;

@Service
@Transactional
public class CustomerWalletServiceImpl implements CustomerWalletService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public CustomerWallet getByCustomerId(Integer customerId) throws Exception {
		List<CustomerWallet> walletList = (List<CustomerWallet>)dao.findForList("CustomerWalletMapper.getByCustomerId", customerId);
		if(walletList != null && walletList.size() > 0){
			return walletList.get(0);
		}
		return null;
	}

	public void updateCustomerPrice(CustomerWallet wallet) throws Exception {
		dao.update("CustomerWalletMapper.updateCustomerPrice", wallet);
	}

	public Integer save(CustomerWallet wallet) throws Exception {
		dao.saveReturnId("CustomerWalletMapper.save", wallet);
		return wallet.getId();
	}
	
	

}
