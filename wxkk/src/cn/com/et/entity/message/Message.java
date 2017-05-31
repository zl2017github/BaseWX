package cn.com.et.entity.message;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 短信信息
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class Message  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 电话*/
	private String mobile;
	
	/** 内容 */
	private String content;
	
	/** 返回状态*/
	private String returnStatus;
	
	/** 返回信息提示 */
	private String message;
	
	/** 返回余额 */
	private String remainPoint;
	
	/** 返回任务批次*/
	private String taskId;
	
	/** 返回成功条数*/
	private String successCounts;
	
	/** 返回的字符串 */
	private String returnString;
	
	/** 创建时间 */
	private Date createTime;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemainPoint() {
		return remainPoint;
	}

	public void setRemainPoint(String remainPoint) {
		this.remainPoint = remainPoint;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(String successCounts) {
		this.successCounts = successCounts;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
