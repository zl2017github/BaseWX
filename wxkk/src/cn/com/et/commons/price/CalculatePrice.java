package cn.com.et.commons.price;

import java.util.Date;

import cn.com.et.utils.DateUtil;

public class CalculatePrice {
	
	//1.0版本，，每个小时1块钱，超过五个小时，算5元
	private static final int price = 1;

	/**
	 * 
	 * 功能描述：根据开始时间，结束时间计算价格
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Double beginAndEndCalculatePrice(Date beginDate,Date endDate){
		//得到骑行所用的时间分钟
		int min = DateUtil.getDiffMin(beginDate,endDate);
		//算出骑行的小时数
		int hour = min/60 + 1;
		double price_ = hourCalPrice(hour);
		return price_;
	}
	
	/**
	 * 
	 * 功能描述：根据小时数得到钱
	 * 		  1.0版本，，每个小时1块钱，超过五个小时，算5元
	 *
	 * @param hour
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int hourCalPrice(int hour){
		if( hour <= 5){
			return hour * price;
		}else{
			return 5;
		}
	}
}
