package cn.com.et.entity.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 用户表
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class Customer  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 手机号 */
	private String mobile;
	
	/** 密码(暂时不用) */
	private String password;
	
	/** 微信id */
	private String wechatId;
	
	/** 是否租了房子*/
	private int isRent;
	
	/** 是否在用车状态（0. 无 1.有）*/
	private int isUsed;
	
	/** 是否缴纳押金(0.无 1.有)*/
	private int isPayDeposit;
	
	/**　是否实名制认证(0.未认证 1.已认证 2.待认证)*/
	private int isCertification;
	
	/** 是否有效(0.无效 1.有效) */
	private int isValid;
	
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public int getIsRent() {
		return isRent;
	}

	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getIsPayDeposit() {
		return isPayDeposit;
	}

	public void setIsPayDeposit(int isPayDeposit) {
		this.isPayDeposit = isPayDeposit;
	}

	public int getIsCertification() {
		return isCertification;
	}

	public void setIsCertification(int isCertification) {
		this.isCertification = isCertification;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
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

}
