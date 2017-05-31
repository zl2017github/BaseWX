package cn.com.et.service.customer;

import cn.com.et.entity.customer.CustomerDeduction;

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
public interface CustomerDeductionService {

	/**
	 * 
	 * 功能描述：通过用户id获取可以抵扣信息
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
	public CustomerDeduction getByCustomerId(Integer customerId) throws Exception;
	
	/**
	 * 
	 * 功能描述：更新用户的消费总额和可抵扣钱数
	 *
	 * @param deduction
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public void updatePrice(CustomerDeduction deduction) throws Exception;
	
	/**
	 * 
	 * 功能描述：新增
	 *
	 * @param deduction
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(CustomerDeduction deduction) throws Exception;
}
