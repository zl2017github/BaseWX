package cn.com.et.service.bike;

import cn.com.et.entity.bike.BikeReport;

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
public interface BikeReportService {
	
	/**
	 * 
	 * 功能描述：保存
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
	public Integer save(BikeReport report) throws Exception;
}
