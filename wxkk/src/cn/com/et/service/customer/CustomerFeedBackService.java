package cn.com.et.service.customer;

import cn.com.et.entity.customer.CustomerFeedBack;

/**
 * 
 * 意见service
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-22
 */
public interface CustomerFeedBackService {

	/**
	 * 
	 * 功能描述：保存意见
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
	public Integer save(CustomerFeedBack back) throws Exception;
}
