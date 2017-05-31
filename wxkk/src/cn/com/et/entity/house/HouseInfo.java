package cn.com.et.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class HouseInfo  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 房屋位置 */
	private String location;
	
	/** 是否缴纳房租(0.未缴纳 1.已缴纳) */
	private Integer isRent;
	
	/** 备注 */
	private String note;
	
	/** 房租 */
	private BigDecimal price;
	
	/** 是否有效（0.否 1.是）*/
	private Integer isValid;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getIsRent() {
		return isRent;
	}

	public void setIsRent(Integer isRent) {
		this.isRent = isRent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
