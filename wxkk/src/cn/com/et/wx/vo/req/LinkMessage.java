/**
 * comment：LinkMessage.java
 * data  HP
 * author:jianglq
 */

/**
 * comment：LinkMessage.java
 * data  HP
 * author 2015
 */
package cn.com.et.wx.vo.req;


/**
 * 链接消息
 * @author jianglq
 * @time 下午04:59:17
 */
public class LinkMessage extends BaseMessage {
	// 消息标题   
    private String Title;  
    // 消息描述   
    private String Description;  
    // 消息链接   
    private String Url;
    
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
    

}
