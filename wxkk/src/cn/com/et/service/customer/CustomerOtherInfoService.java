package cn.com.et.service.customer;

import cn.com.et.entity.customer.CustomerOtherInfo;

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
public interface CustomerOtherInfoService {
	/**
	 * 
	 * 功能描述：保存
	 *
	 * @param customer
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(CustomerOtherInfo customerOtherInfo)throws Exception;
	
	/**
	 * 
	 * 功能描述：根据身份证查找信息
	 *
	 * @param customer
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public CustomerOtherInfo getByIdNum(String idNum) throws Exception;
}
