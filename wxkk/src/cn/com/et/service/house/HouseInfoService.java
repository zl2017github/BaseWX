package cn.com.et.service.house;

import java.util.List;

import cn.com.et.entity.house.HouseInfo;
import cn.com.et.plugin.page.Page;
import cn.com.et.plugin.page.PageData;

/**
 * 
 * 业主房屋信息
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-14
 */
public interface HouseInfoService {
	
	/**
	 * 
	 * 功能描述：获取列表
	 *
	 * @param page
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-14
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public List<PageData> houseInfoList(Page page) throws Exception ;
}
