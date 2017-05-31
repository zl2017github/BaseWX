package cn.com.et.entity.house;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 用户房屋
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class HouseHistory  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 1. 房屋 2.车辆*/
	private Integer type;
	
	/** 外键id */
	private Integer foreignKeyId;
	
	/** 操作记录*/
	private String text;
	
	/** 创建时间 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getForeignKeyId() {
		return foreignKeyId;
	}

	public void setForeignKeyId(Integer foreignKeyId) {
		this.foreignKeyId = foreignKeyId;
	}

}
