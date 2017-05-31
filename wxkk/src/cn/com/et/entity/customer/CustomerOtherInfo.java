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
public class CustomerOtherInfo  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户ID */
	private Integer tCustomerId;
	
	/** 姓名 */
	private String name;
	
	/** 昵称 */
	private String nick;
	
	/** 性别(0.女 1.男) */
	private Integer sex;
	
	/** 生日 */
	private String birthday;
	
	/** 认证方式（1. 身份证号认证 2.身份证认证）*/
	private Integer type;
	
	/** 身份证号*/
	private String idNumber;
	
	/** 身份照图片 */
	private String cerPicture;
	
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

	public Integer gettCustomerId() {
		return tCustomerId;
	}

	public void settCustomerId(Integer tCustomerId) {
		this.tCustomerId = tCustomerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCerPicture() {
		return cerPicture;
	}

	public void setCerPicture(String cerPicture) {
		this.cerPicture = cerPicture;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
}
