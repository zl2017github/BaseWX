package cn.com.et.service.message;

import cn.com.et.entity.message.Message;

/**
 * 
 * 短息service
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-14
 */
public interface MessageService {
	
	/**
	 * 
	 * 功能描述：保存信息
	 *
	 * @param page
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-5-14
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(Message message) throws Exception ;
}
