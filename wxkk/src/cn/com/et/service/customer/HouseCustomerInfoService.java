package cn.com.et.service.customer;

import cn.com.et.entity.house.HouseCustomerInfo;

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
public interface HouseCustomerInfoService {

	/**
	 * 
	 * 功能描述：通过微信id获取用户
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
	public HouseCustomerInfo getByUserMobile(String mobile) throws Exception;
}
