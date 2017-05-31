package cn.com.et.ident;

import java.io.Serializable;

public class IdentBase implements Serializable{
	private static final long serialVersionUID = 1L;
	//创建时间（毫秒）
	protected long createTime;
	//多长时间失效（秒）
	protected long expires;
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	/**
	 * 根据当前时间计算凭据对象是否有效
	 * @param curTime 毫秒
	 * @return
	 */
	public boolean ifValidate(long curTime){
		return ifValidate(curTime,0L);
	}
	
	/**
	 * 根据当前时间计算凭据对象是否有效
	 * @param curTime 毫秒
	 * @param off 秒
	 * @return
	 */
	public boolean ifValidate(long curTime,long off){
		boolean bln = false;
		if(expires<1){
			try {
				throw new Exception("The parameter expires is not valid, and the argument must be an integer greater than 1.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long nowExpires = curTime-this.getCreateTime();
		if((nowExpires/1000) < (this.expires-off)){
			bln = true;
		}
		return bln;
	}
}
