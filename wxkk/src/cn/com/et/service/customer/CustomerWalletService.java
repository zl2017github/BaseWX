package cn.com.et.service.customer;

import cn.com.et.entity.customer.CustomerWallet;

/**
 * 
 * 用户service
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-22
 */
public interface CustomerWalletService {

	/**
	 * 
	 * 功能描述：通过用户id获取用户钱包
	 *
	 * @param wechatId
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-22
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public CustomerWallet getByCustomerId(Integer customerId) throws Exception;
	
	/**
	 * 
	 * 功能描述：更新用户的余额
	 *
	 * @param wallet
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public void updateCustomerPrice(CustomerWallet wallet) throws Exception;
	
	/**
	 * 
	 * 功能描述：新增钱包
	 *
	 * @param wallet
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(CustomerWallet wallet) throws Exception;
}
