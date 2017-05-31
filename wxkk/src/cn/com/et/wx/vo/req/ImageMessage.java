/**
 * comment：ImageMessage.java
 * data  HP
 * author:jianglq
 */

/**
 * comment：ImageMessage.java
 * data  HP
 * author 2015
 */
package cn.com.et.wx.vo.req;


/**
 * 图片消息
 * @author jianglq
 * @time 下午04:56:37
 */
public class ImageMessage  extends BaseMessage {
	// 图片链接   
    private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}
	
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
   
}
