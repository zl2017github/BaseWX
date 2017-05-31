package cn.com.et.service.customer;

import cn.com.et.entity.customer.Customer;

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
public interface CustomerService {

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
	public Customer getByWechatId(String wechatId) throws Exception;
	
	/**
	 * 
	 * 功能描述：用户登录，检查用户名密码
	 *
	 * @param customer
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Customer login(Customer customer) throws Exception;
	
	/**
	 * 
	 * 功能描述：根据手机号得到用户信息
	 *
	 * @param customer
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Customer getByMobile(String mobile) throws Exception;
	
	/**
	 * 
	 * 功能描述：更新用户wechatId
	 *
	 * @param customer
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public void updateWechatId(Customer customer) throws Exception;
	
	
	/**
	 * 
	 * 功能描述：修改用户用车状态或不用车状态
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
	public void updateCustomerIsUsed(Customer customer) throws Exception;
	
	/**
	 * 
	 * 功能描述：修改用户已认证
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
	public void updateCustomerIsCertification(Customer customer) throws Exception;
	
	/**
	 * 
	 * 功能描述：修改用户已缴纳押金
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
	public void updateCustomerIsPayDeposit(Customer customer) throws Exception;
	
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
	public Integer save(Customer customer)throws Exception;
}
