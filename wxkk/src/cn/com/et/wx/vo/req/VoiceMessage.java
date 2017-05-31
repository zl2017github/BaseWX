/**
 * comment：VoiceMessage.java
 * data  HP
 * author:jianglq
 */

/**
 * comment：VoiceMessage.java
 * data  HP
 * author 2015
 */
package cn.com.et.wx.vo.req;


/**
 * 语音消息
 * @author jianglq
 * @time 下午05:00:51
 */
public class VoiceMessage extends BaseMessage {
	// 媒体ID   
    private String MediaId;  
    // 语音格式   
    private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}

}
