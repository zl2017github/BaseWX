package cn.com.et.entity.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 用户意见反馈表
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class CustomerFeedBack  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id */
	private Integer customerId;
	
	/** 意见 */
	private String text;
	
	/** 是否有效(0.无效 1.有效) */
	private Integer isValid;
	
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
