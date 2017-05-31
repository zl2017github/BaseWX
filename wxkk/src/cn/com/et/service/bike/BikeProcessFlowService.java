package cn.com.et.service.bike;

import java.util.List;
import cn.com.et.entity.bike.BikeProcessFlow;

/**
 * 
 * 流水service
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-12
 */
public interface BikeProcessFlowService {
	
	/**
	 * 
	 * 功能描述：查询流水
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-13
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public List<BikeProcessFlow> bikeProcessFlowList(BikeProcessFlow processFlow) throws Exception;
	
	/**
	 * 
	 * 功能描述：插入流水记录
	 *
	 * @param flow
	 * @return
	 * @throws Exception
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-14
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public Integer save(BikeProcessFlow flow) throws Exception;
}
