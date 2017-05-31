package cn.com.et.service.bike;

import java.util.List;

import cn.com.et.entity.bike.BikeHistory;

/**
 * 
 * 自行车接口
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-11
 */
public interface BikeHistoryService {
	
	/**
	 * 
	 * 功能描述：获取自行车历史行程
	 *
	 * @param merchantId
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public List<BikeHistory> getByCustomerId(Integer customerId) throws Exception;
	
	/**
	 * 
	 * 功能描述：保存车辆历史信息
	 *
	 * @param bikeHistory
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(BikeHistory bikeHistory) throws Exception;
	
	/**
	 * 
	 * 功能描述：根据id进行查询
	 *
	 * @param historyId
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public BikeHistory getById(Integer id) throws Exception;
	
	/**
	 * 
	 * 功能描述：结束骑行，更新信息
	 *
	 * @param bikeHistory
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public void updateFinishBike(BikeHistory bikeHistory) throws Exception;
	
	/**
	 * 
	 * 功能描述：将行程置为无效状态
	 *
	 * @param historyId
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-5-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public void updateToIsNotvalid(Integer customerId) throws Exception;
}
