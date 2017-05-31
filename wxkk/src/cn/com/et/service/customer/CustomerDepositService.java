package cn.com.et.service.customer;

import cn.com.et.entity.customer.CustomerDeposit;

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
public interface CustomerDepositService {

	/**
	 * 
	 * 功能描述:新增退款申请
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
	public Integer save(CustomerDeposit deposit) throws Exception;
}
