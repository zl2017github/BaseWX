package cn.com.et.service.bike;

import java.util.List;

import cn.com.et.entity.bike.BikeInfo;

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
public interface BikeService {
	
	/**
	 * 
	 * 功能描述：根据车牌号得到车辆信息
	 *
	 * @param bikeNum
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public BikeInfo getByBikeNum(String bikeNum) throws Exception;
	
	/**
	 * 
	 * 功能描述：根据id获取车辆信息
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public BikeInfo getById(Integer id) throws Exception;
}
