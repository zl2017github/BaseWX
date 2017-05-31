package cn.com.et.commons.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.et.constant.GlobalConstant;
import cn.com.et.wx.vo.menu.ReceiveXmlEntity;
import cn.com.et.wx.vo.rsp.Article;
import cn.com.et.wx.vo.rsp.NewsMessage;
import cn.com.et.wx.vo.rsp.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WXXmlUtil {
	private static Logger logger = LoggerFactory.getLogger(GlobalConstant.WX_LOG_NAME);  
	/** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) {  
    	// 将解析结果存储在HashMap中   
    	Map<String, String> map = new HashMap<String, String>();  
    	InputStream inputStream = null;
    	try {
			// 从request中取得输入流
			inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
		}

		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	public static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String handlerXml(String strxml) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(strxml.replaceAll("__", "_"));
			strxml = document.asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return strxml;
	}

	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

	/**
	 * 解析接收到的微信xml，返回消息对象
	 * 
	 * @param strXml
	 * @return
	 */
	public ReceiveXmlEntity getMsgEntity(String strXml) {
		ReceiveXmlEntity msg = null;
		try {
			if (strXml.length() <= 0 || strXml == null)
				return null;

			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(strXml);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 遍历根节点下所有子节点
			Iterator<?> iter = root.elementIterator();

			// 遍历所有结点
			msg = new ReceiveXmlEntity();
			// 利用反射机制，调用set方法
			// 获取该实体的元类型
			Class<?> c = Class.forName("cn.com.et.wx.vo.menu.ReceiveXmlEntity");
			msg = (ReceiveXmlEntity) c.newInstance();// 创建这个实体的对象

			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				// 获取set方法中的参数字段（实体类的属性）
				Field field = c.getDeclaredField(ele.getName());
				// 获取set方法，field.getType())获取它的参数数据类型
				Method method = c.getDeclaredMethod("set" + ele.getName(),field.getType());
				// 调用set方法
				method.invoke(msg, ele.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("xml 格式异常: " + strXml);
			e.printStackTrace();
		}
		return msg;
	}
}
